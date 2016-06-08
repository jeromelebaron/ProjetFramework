/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.afcepf.atod26.framework.api.IAction;
import fr.afcepf.atod26.framework.api.IActionForm;
import fr.afcepf.atod26.framework.api.IConfig;

/**
 * L'implémentation pour la configuration du framework avec le fichier XML.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class FactoryXMLConfig implements IConfig {

    /**
     * Singleton
     */
    private static FactoryXMLConfig factoryXMLConfig;
    /**
     * Pour faire du log.
     */
    private static final Logger LOGGER = Logger.getLogger(FactoryXMLConfig.class);
    /**
     * Le chemin du fichier de configuration.
     */
    private static String pathFichier;
    /**
     * La fabrique pour construire le parser XML.
     */
    private static DocumentBuilderFactory factory;
    /**
     * Pour construire le parser XML.
     */
    private static DocumentBuilder builder;
    /**
     * Le document XML parsé.
     */
    private static Document document;
    /**
     * La racine du document XML.
     */
    private static Element racine;
    /**
     * Pour récupérer le chemin du fichier au chargement de l'application et construire les
     * différents éléments nécessaires à la récupération des éléments dans le fichier XML.
     */
    static {
        pathFichier = Thread.currentThread().getContextClassLoader().getResource("config.xml")
                .getPath();
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(pathFichier);
            racine = document.getDocumentElement();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Constructeur privé.
     */
    private FactoryXMLConfig() {
        // EMPTY
    }

    /**
     * Pour récupérer l'instance du singleton de la classe.
     * @return l'instance de {@link FactoryXMLConfig}.
     */
    public static FactoryXMLConfig getInstance() {
        return factoryXMLConfig;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, IAction> remplirMapAction() {
        Map<String, IAction> mapping = new HashMap<>();
        try {
            NodeList actions = racine.getElementsByTagName("action");
            for (int localI = 0; localI < actions.getLength(); localI++) {
                Node base = actions.item(localI);
                NodeList elementsActions = base.getChildNodes();
                String urlPattern = recupererContenuNoeud(elementsActions, "url-pattern");
                String classPattern = recupererContenuNoeud(elementsActions, "action-name");
                IAction action = (IAction) Class.forName(classPattern).newInstance();
                mapping.put(urlPattern, action);
            }
        } catch (DOMException | InstantiationException | IllegalAccessException
                | ClassNotFoundException e) {
            LOGGER.error(e);
        }
        return mapping;
    }

    /**
     * Pour récupérer le contenu d'un noeud en fonction de la balise.
     * @param listeNoeud la liste des noeuds à parcourir.
     * @param nomNoeud le nom de la balise.
     * @return la contenu de le balise.
     */
    private static String recupererContenuNoeud(NodeList listeNoeud, String nomNoeud) {
        String urlPattern = null;
        for (int localI2 = 0; localI2 < listeNoeud.getLength(); localI2++) {
            Node enfant = listeNoeud.item(localI2);
            if (nomNoeud.equals(enfant.getNodeName())) {
                urlPattern = enfant.getTextContent();
            }
        }
        return urlPattern;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, IActionForm> remplirMapForm() {
        Map<String, IActionForm> lesActionsForms = new HashMap<>();
        try {
            NodeList actions = racine.getElementsByTagName("form");
            for (int localI = 0; localI < actions.getLength(); localI++) {
                Node base = actions.item(localI);
                NodeList elementsActions = base.getChildNodes();
                String urlPattern = recupererContenuNoeud(elementsActions, "form-name");
                String classPattern = recupererContenuNoeud(elementsActions, "form-class");
                IActionForm action = (IActionForm) Class.forName(classPattern).newInstance();
                lesActionsForms.put(urlPattern, action);
            }
        } catch (DOMException | InstantiationException | IllegalAccessException
                | ClassNotFoundException e) {
            LOGGER.error(e);
        }
        return lesActionsForms;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> remplirMap(String paramTagParent, String paramCle, String paramValeur) {
        Map<String, String> correspondanceActionForm = new HashMap<>();
        try {
            NodeList actions = racine.getElementsByTagName(paramTagParent);
            for (int localI = 0; localI < actions.getLength(); localI++) {
                Node base = actions.item(localI);
                NodeList elementsActions = base.getChildNodes();
                String urlPattern = recupererContenuNoeud(elementsActions, paramCle);
                String classPattern = recupererContenuNoeud(elementsActions, paramValeur);
                correspondanceActionForm.put(urlPattern, classPattern);
            }
        } catch (DOMException e) {
            LOGGER.error(e);
        }
        return correspondanceActionForm;
    }

    /**
     * {@inheritDoc}
     */
    public static Map<String, Object> remplirMapBeans() {
        Map<String, Object> lesBeans = new HashMap<>();
        try {
            NodeList actions = racine.getElementsByTagName("bean");
            for (int localI = 0; localI < actions.getLength(); localI++) {
                Node base = actions.item(localI);
                final String id = recuperAttributNoeud(base, "id");
                final String instance = recuperAttributNoeud(base, "class");
                Map<String, String> proprieteBean = null;
                if (base.hasChildNodes()) {
                    proprieteBean = recupererProprieteBean(base);
                }
                final Object nouvelleInstance = recupererInstance(instance);
                setSingleton(nouvelleInstance);
                if (proprieteBean != null && !proprieteBean.isEmpty()) {
                    setDependance(nouvelleInstance, proprieteBean, lesBeans);
                }
                lesBeans.put(id, nouvelleInstance);
            }
        } catch (DOMException | InstantiationException | IllegalAccessException
                | ClassNotFoundException | NoSuchMethodException | IllegalArgumentException
                | InvocationTargetException e) {
            LOGGER.error(e);
        }
        return lesBeans;
    }

    /**
     * Pour récupérer le contenu d'une propriété d'un noeud.
     * @param noeud
     * @param paramAttribut
     * @return
     */
    private static String recuperAttributNoeud(Node noeud, String paramAttribut) {
        NamedNodeMap listeAttribut = noeud.getAttributes();
        String valeurAttribut = null;
        for (int localI = 0; localI < listeAttribut.getLength(); localI++) {
            if (paramAttribut.equals(listeAttribut.item(localI).getNodeName())) {
                valeurAttribut = listeAttribut.item(localI).getTextContent();
            }
        }
        return valeurAttribut;
    }

    /**
     * Pour récupérer les valeurs de la balise <code>property</code> d'une balise <code>bean</code>.
     * @param noeud
     * @return
     */
    private static Map<String, String> recupererProprieteBean(Node noeud) {
        final Map<String, String> proprieteBean = new HashMap<>();
        Node noeudProperty = null;
        NodeList listeNoeud = noeud.getChildNodes();
        for (int localI = 0; localI < listeNoeud.getLength(); localI++) {
            Node enfant = listeNoeud.item(localI);
            if ("property".equals(enfant.getNodeName())) {
                noeudProperty = enfant;
            }
        }
        final String cle = recuperAttributNoeud(noeudProperty, "ref");
        final String valeur = recuperAttributNoeud(noeudProperty, "name");
        proprieteBean.put(cle, valeur);
        return proprieteBean;
    }

    /**
     * Pour récupérer une instance d'un objet même si son constructeur est privé.
     * @param nomClasse le nom complet de la classe pour laquelle créer l'instance.
     * @return un objet du type de la classe passée en paramètre.
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static Object recupererInstance(String nomClasse) throws NoSuchMethodException,
            ClassNotFoundException, InstantiationException, IllegalAccessException,
            InvocationTargetException {
        final Constructor<?> constructeur = Class.forName(nomClasse).getDeclaredConstructor();
        constructeur.setAccessible(true);
        return constructeur.newInstance();
    }

    /**
     * Pour setter le singleton de la classe.
     * @param paramNouvelleInstance l'instance pour laquelle setter le singleton.
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    private static void setSingleton(Object paramNouvelleInstance) throws IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        Class<? extends Object> c = paramNouvelleInstance.getClass();
        String nomSetter = "set" + c.getSimpleName();
        Method setSingleton = c.getMethod(nomSetter, paramNouvelleInstance.getClass());
        setSingleton.invoke(c, paramNouvelleInstance);
    }

    /**
     * Accesseur en écriture du champ <code>factoryXMLConfig</code>.
     * @param paramFactoryXMLConfig la valeur à écrire dans <code>factoryXMLConfig</code>.
     */
    public static void setFactoryXMLConfig(FactoryXMLConfig paramFactoryXMLConfig) {
        factoryXMLConfig = paramFactoryXMLConfig;
    }

    /**
     * Pour setter les attributs par injection de dépendance.
     * @param paramNouvelleInstance
     * @param paramProprieteBean
     * @param paramLesBeans
     * @throws IllegalAccessException
     */
    private static void setDependance(Object paramNouvelleInstance,
            Map<String, String> paramProprieteBean, Map<String, Object> paramLesBeans)
            throws IllegalAccessException {
        for (String clefBean : paramLesBeans.keySet()) {
            if (paramProprieteBean.get(clefBean) != null) {
                Class<? extends Object> c = paramNouvelleInstance.getClass();
                final Field[] lesAttributs = c.getDeclaredFields();
                for (Field localField : lesAttributs) {
                    if (paramProprieteBean.get(clefBean).equals(localField.getName())) {
                        localField.setAccessible(true);
                        localField.set(paramNouvelleInstance, paramLesBeans.get(clefBean));
                    }
                }
            }
        }

    }

}

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
        LOGGER.debug("Chargement des ressources");
        pathFichier = Thread.currentThread().getContextClassLoader().getResource("config.xml")
                .getPath();
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(pathFichier);
            racine = document.getDocumentElement();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.error("Erreur avec le fichier de configuration", e);
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
        LOGGER.debug("Méthode remplirMapAction");
        Map<String, IAction> mapping = new HashMap<>();
        try {
            NodeList actions = racine.getElementsByTagName("action");
            for (int localI = 0; localI < actions.getLength(); localI++) {
                final Node base = actions.item(localI);
                final NodeList elementsActions = base.getChildNodes();
                final String urlPattern = recupererContenuNoeud(elementsActions, "url-pattern");
                final String classPattern = recupererContenuNoeud(elementsActions, "action-name");
                final IAction action = (IAction) Class.forName(classPattern).newInstance();
                mapping.put(urlPattern, action);
            }
        } catch (DOMException | InstantiationException | IllegalAccessException
                | ClassNotFoundException e) {
            LOGGER.error("Erreur lors du remplissage des actions", e);
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
        LOGGER.debug("Méthode recupererContenuNoeud");
        LOGGER.debug("Param nomNoeud " + nomNoeud);
        String urlPattern = null;
        for (int localI2 = 0; localI2 < listeNoeud.getLength(); localI2++) {
            final Node enfant = listeNoeud.item(localI2);
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
        LOGGER.debug("Méthode remplirMapForm");
        Map<String, IActionForm> lesActionsForms = new HashMap<>();
        try {
            NodeList actions = racine.getElementsByTagName("form");
            for (int localI = 0; localI < actions.getLength(); localI++) {
                final Node base = actions.item(localI);
                final NodeList elementsActions = base.getChildNodes();
                final String urlPattern = recupererContenuNoeud(elementsActions, "form-name");
                final String classPattern = recupererContenuNoeud(elementsActions, "form-class");
                final IActionForm action = (IActionForm) Class.forName(classPattern).newInstance();
                lesActionsForms.put(urlPattern, action);
            }
        } catch (DOMException | InstantiationException | IllegalAccessException
                | ClassNotFoundException e) {
            LOGGER.error("Erreur lors du remplissage des formulaires", e);
        }
        return lesActionsForms;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> remplirMap(String paramTagParent, String paramCle, String paramValeur) {
        LOGGER.debug("Méthode remplirMap");
        LOGGER.debug("Param paramTagParent " + paramTagParent);
        LOGGER.debug("Param paramCle " + paramCle);
        LOGGER.debug("Param paramValeur " + paramValeur);
        Map<String, String> correspondanceActionForm = new HashMap<>();
        try {
            final NodeList actions = racine.getElementsByTagName(paramTagParent);
            for (int localI = 0; localI < actions.getLength(); localI++) {
                final Node base = actions.item(localI);
                final NodeList elementsActions = base.getChildNodes();
                final String urlPattern = recupererContenuNoeud(elementsActions, paramCle);
                final String classPattern = recupererContenuNoeud(elementsActions, paramValeur);
                correspondanceActionForm.put(urlPattern, classPattern);
            }
        } catch (DOMException e) {
            LOGGER.error("Erreur lors du remplissage", e);
        }
        return correspondanceActionForm;
    }

    /**
     * {@inheritDoc}
     */
    public static Map<String, Object> remplirMapBeans() {
        LOGGER.debug("Méthode remplirMapBeans");
        Map<String, Object> lesBeans = new HashMap<>();
        try {
            NodeList actions = racine.getElementsByTagName("bean");
            for (int localI = 0; localI < actions.getLength(); localI++) {
                final Node base = actions.item(localI);
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
            LOGGER.error("Erreur lors du remplissage des beans", e);
        }
        return lesBeans;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Map<String, String>> remplirMapForward() {
        LOGGER.debug("Méthode remplirMapForward");
        Map<String, Map<String, String>> lesForwards = new HashMap<>();
        try {
            final NodeList actions = racine.getElementsByTagName("action");
            for (int localI = 0; localI < actions.getLength(); localI++) {
                final Node base = actions.item(localI);
                final NodeList elementsActions = base.getChildNodes();
                final Map<String, String> secondeMap = new HashMap<>();
                String cle = null;
                for (int localI2 = 0; localI2 < elementsActions.getLength(); localI2++) {
                    Node noeud = elementsActions.item(localI2);
                    if ("action-name".equals(noeud.getNodeName())) {
                        cle = noeud.getTextContent();
                    }
                    if ("forward".equals(noeud.getNodeName())) {
                        final String cleSecondeMap = recuperAttributNoeud(noeud, "name");
                        final String valeurSecondeMap = recuperAttributNoeud(noeud, "path");
                        secondeMap.put(cleSecondeMap, valeurSecondeMap);
                    }
                }
                lesForwards.put(cle, secondeMap);
            }
        } catch (DOMException e) {
            LOGGER.error("Erreur lors du remplissage des forward", e);
        }
        return lesForwards;
    }

    /**
     * Pour récupérer le contenu d'une propriété d'un noeud.
     * @param noeud le noeud à scanner.
     * @param paramAttribut le nom de la balise.
     * @return le contenu du noeud.
     */
    private static String recuperAttributNoeud(Node noeud, String paramAttribut) {
        LOGGER.debug("Méthode recuperAttributNoeud");
        LOGGER.debug("Param paramAttribut " + paramAttribut);
        final NamedNodeMap listeAttribut = noeud.getAttributes();
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
     * @param noeud pour récupérer le contenu des <code>property</code> d'un bean.
     * @return une map avec en clé la ref du bean (la propriété vers laquelle faire l'injection) et
     *         en valeur le nom qui correspond à l'id du bean à partir duquel faire l'injection
     */
    private static Map<String, String> recupererProprieteBean(Node noeud) {
        LOGGER.debug("Méthode recupererProprieteBean");
        final Map<String, String> proprieteBean = new HashMap<>();
        Node noeudProperty = null;
        final NodeList listeNoeud = noeud.getChildNodes();
        for (int localI = 0; localI < listeNoeud.getLength(); localI++) {
            final Node enfant = listeNoeud.item(localI);
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
     * @throws NoSuchMethodException au cas ou.
     * @throws ClassNotFoundException au cas ou.
     * @throws InstantiationException au cas ou.
     * @throws IllegalAccessException au cas ou.
     * @throws InvocationTargetException au cas ou.
     */
    private static Object recupererInstance(String nomClasse) throws NoSuchMethodException,
            ClassNotFoundException, InstantiationException, IllegalAccessException,
            InvocationTargetException {
        LOGGER.debug("Méthode recupererInstance");
        LOGGER.debug("Param nomClasse " + nomClasse);
        final Constructor<?> constructeur = Class.forName(nomClasse).getDeclaredConstructor();
        constructeur.setAccessible(true);
        return constructeur.newInstance();
    }

    /**
     * Pour setter le singleton de la classe.
     * @param paramNouvelleInstance l'instance pour laquelle setter le singleton.
     * @throws IllegalAccessException au cas ou.
     * @throws NoSuchMethodException au cas ou.
     * @throws InvocationTargetException au cas ou.
     */
    private static void setSingleton(Object paramNouvelleInstance) throws IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        LOGGER.debug("Méthode setSingleton");
        final Class<? extends Object> c = paramNouvelleInstance.getClass();
        final String nomSetter = "set" + c.getSimpleName();
        final Method setSingleton = c.getMethod(nomSetter, paramNouvelleInstance.getClass());
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
     * @param paramNouvelleInstance la nouvelle instance à injecter.
     * @param paramProprieteBean la map avec les propriétés des bean.
     * @param paramLesBeans la liste des beans.
     * @throws IllegalAccessException au cas ou.
     */
    private static void setDependance(Object paramNouvelleInstance,
            Map<String, String> paramProprieteBean, Map<String, Object> paramLesBeans)
            throws IllegalAccessException {
        LOGGER.debug("Méthode setDependance");
        for (String clefBean : paramLesBeans.keySet()) {
            if (paramProprieteBean.get(clefBean) != null) {
                final Class<? extends Object> c = paramNouvelleInstance.getClass();
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

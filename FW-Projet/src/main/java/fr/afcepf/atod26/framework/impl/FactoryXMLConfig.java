/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.HTMLReader.FormAction;
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
import fr.afcepf.atod26.framework.impl.entity.ActionXML;
import fr.afcepf.atod26.framework.impl.entity.FormXML;
import fr.afcepf.atod26.framework.impl.entity.ForwardXML;

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
    public Map<String, ActionXML> remplirMapAction() {
        LOGGER.debug("Méthode remplirMapAction");
        Map<String, ActionXML> mapping = new HashMap<>();
        try {
            NodeList actions = racine.getElementsByTagName("action");
            for (int localI = 0; localI < actions.getLength(); localI++) {
                final Node base = actions.item(localI);
                final NodeList elementsActions = base.getChildNodes();
                final ActionXML localActionXML = new ActionXML();
                final String urlPattern = construireActionXML(elementsActions, localActionXML);
                mapping.put(urlPattern, localActionXML);
            }
        } catch (DOMException | InstantiationException | IllegalAccessException
                | ClassNotFoundException e) {
            LOGGER.error("Erreur lors du remplissage des actions", e);
        }
        return mapping;
    }

    /**
     * Pour construire une action XML à partir des balises contenu dans la basise
     * <code>action</code>
     * @param elementsActions la liste des enfants de <code>action</code>
     * @param localActionXML l'actionXML à peupler
     * @return l'url pattern associée à l'action.
     * @throws InstantiationException au cas ou.
     * @throws IllegalAccessException au cas ou.
     * @throws ClassNotFoundException au cas ou.
     */
    private String construireActionXML(final NodeList elementsActions,
            final ActionXML localActionXML) throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        String urlPattern = null;
        for (int localI2 = 0; localI2 < elementsActions.getLength(); localI2++) {
            final Node enfant = elementsActions.item(localI2);
            if ("url-pattern".equals(enfant.getNodeName())) {
                localActionXML.setUrlPattern(enfant.getTextContent());
                urlPattern = enfant.getTextContent();
            }
            if ("action-name".equals(enfant.getNodeName())) {
                final IAction action = (IAction) Class.forName(enfant.getTextContent())
                        .newInstance();
                localActionXML.setAction(action);
            }
            if ("form-name".equals(enfant.getNodeName())) {
                localActionXML.setFormName(enfant.getTextContent());
            }
            if ("from-view".equals(enfant.getNodeName())) {
                localActionXML.setFromView(enfant.getTextContent());
            }
            if ("forwards".equals(enfant.getNodeName())) {
                localActionXML.setForwardXMLs(recupererForward(enfant));
            }
        }
        return urlPattern;
    }

    /**
     * Pour récupérer les balises <code>forward</code> d'un noeud <code>forwards</code>.
     * @param paramEnfant le noeud dans lequel chercher les balises.
     * @return une liste de {@link ForwardXML}.
     */
    private List<ForwardXML> recupererForward(Node paramEnfant) {
        List<ForwardXML> localForwardXMLs = new ArrayList<>();
        NodeList lesForward = paramEnfant.getChildNodes();
        for (int localI2 = 0; localI2 < lesForward.getLength(); localI2++) {
            final Node enfant = lesForward.item(localI2);
            if ("forward".equals(enfant.getNodeName())) {
                final String name = recuperAttributNoeud(enfant, "name");
                final String path = recuperAttributNoeud(enfant, "path");
                final ForwardXML localForwardXML = new ForwardXML(name, path);
                localForwardXMLs.add(localForwardXML);
            }
        }
        return localForwardXMLs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, FormXML> remplirMapForm() {
        LOGGER.debug("Méthode remplirMapForm");
        Map<String, FormXML> lesActionsForms = new HashMap<>();
        try {
            NodeList actions = racine.getElementsByTagName("form");
            for (int localI = 0; localI < actions.getLength(); localI++) {
                final Node base = actions.item(localI);
                final NodeList elementsActions = base.getChildNodes();
                final FormXML localFormXML = new FormXML();
                final String formName = construireFormXML(elementsActions, localFormXML);
                lesActionsForms.put(formName, localFormXML);
            }
        } catch (DOMException | InstantiationException | IllegalAccessException
                | ClassNotFoundException e) {
            LOGGER.error("Erreur lors du remplissage des formulaires", e);
        }
        return lesActionsForms;
    }

    /**
     * Pour construire une action XML à partir des balises contenu dans la basise <code>form</code>
     * @param elementsActions la liste des enfants de <code>form</code>
     * @param localFormXML le {@link FormXML} à peupler
     * @return l'url pattern associée au {@link FormAction}.
     * @throws InstantiationException au cas ou.
     * @throws IllegalAccessException au cas ou.
     * @throws ClassNotFoundException au cas ou.
     */
    private String construireFormXML(final NodeList elementsActions, final FormXML localFormXML)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String formName = null;
        for (int localI2 = 0; localI2 < elementsActions.getLength(); localI2++) {
            final Node enfant = elementsActions.item(localI2);
            if ("form-name".equals(enfant.getNodeName())) {
                localFormXML.setFormName(enfant.getTextContent());
                formName = enfant.getTextContent();
            }
            if ("form-class".equals(enfant.getNodeName())) {
                final IActionForm actionForm = (IActionForm) Class.forName(enfant.getTextContent())
                        .newInstance();
                localFormXML.setActionForm(actionForm);
            }
        }
        return formName;
    }

    /**
     * Pour l'injection de dépendances.
     * @return une map avec les instances.
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
     * Pour récupérer le contenu d'un attribut d'un noeud.
     * @param noeud le noeud à scanner.
     * @param paramAttribut le nom de la balise.
     * @return le contenu du noeud.
     */
    private static String recuperAttributNoeud(Node noeud, String paramAttribut) {
        LOGGER.debug("Méthode recuperAttributNoeud");
        LOGGER.debug("  Param paramAttribut " + paramAttribut);
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
        LOGGER.debug("  Param nomClasse " + nomClasse);
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
        for (Map.Entry<String, Object> entree : paramLesBeans.entrySet()) {
            if (paramProprieteBean.get(entree.getKey()) != null) {
                final Class<? extends Object> c = paramNouvelleInstance.getClass();
                final Field[] lesAttributs = c.getDeclaredFields();
                for (Field localField : lesAttributs) {
                    if (paramProprieteBean.get(entree.getKey()).equals(localField.getName())) {
                        localField.setAccessible(true);
                        localField.set(paramNouvelleInstance, paramLesBeans.get(entree.getKey()));
                    }
                }
            }
        }
    }

    /**
     * Accesseur en écriture du champ <code>factoryXMLConfig</code>.
     * @param paramFactoryXMLConfig la valeur à écrire dans <code>factoryXMLConfig</code>.
     */
    public static void setFactoryXMLConfig(FactoryXMLConfig paramFactoryXMLConfig) {
        factoryXMLConfig = paramFactoryXMLConfig;
    }

}

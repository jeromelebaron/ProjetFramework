/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.afcepf.atod26.framework.api.Action;
import fr.afcepf.atod26.framework.api.ActionForm;

/**
 * Les méthodes pour le chargement de la configuration du fichier XML.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class FactoryConfig {

    /**
     * Pour faire du log.
     */
    private static final Logger LOGGER = Logger.getLogger(FactoryConfig.class);
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
    private FactoryConfig() {
        // EMPTY
    }

    /**
     * Pour construire la Map avec la correspondance entre url pattern et classe correspondante.
     * @return la map remplie.
     */
    public static Map<String, Action> remplirMapAction() {
        Map<String, Action> mapping = new HashMap<>();
        try {
            NodeList actions = racine.getElementsByTagName("action");
            for (int localI = 0; localI < actions.getLength(); localI++) {
                Node base = actions.item(localI);
                NodeList elementsActions = base.getChildNodes();
                String urlPattern = recupererContenuNoeud(elementsActions, "url-pattern");
                String classPattern = recupererContenuNoeud(elementsActions, "action-name");
                Action action = (Action) Class.forName(classPattern).newInstance();
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
     * Pour remplir la map des classes qui implémentent {@link ActionForm} déclarées dans le fichier
     * XML.
     * @return la map avec en clé le form-name et en valeur l'instance de la classe correspondante.
     */
    public static Map<String, ActionForm> remplirMapForm() {
        Map<String, ActionForm> lesActionsForms = new HashMap<>();
        try {
            NodeList actions = racine.getElementsByTagName("form");
            for (int localI = 0; localI < actions.getLength(); localI++) {
                Node base = actions.item(localI);
                NodeList elementsActions = base.getChildNodes();
                String urlPattern = recupererContenuNoeud(elementsActions, "form-name");
                String classPattern = recupererContenuNoeud(elementsActions, "form-class");
                ActionForm action = (ActionForm) Class.forName(classPattern).newInstance();
                lesActionsForms.put(urlPattern, action);
            }
        } catch (DOMException | InstantiationException | IllegalAccessException
                | ClassNotFoundException e) {
            LOGGER.error(e);
        }
        return lesActionsForms;
    }

    /**
     * La map avec la correspondance entre url-pattern et form-name.
     * @return la map avec les données.
     */
    public static Map<String, String> remplirMap() {
        Map<String, String> correspondanceActionForm = new HashMap<>();
        try {
            NodeList actions = racine.getElementsByTagName("action");
            for (int localI = 0; localI < actions.getLength(); localI++) {
                Node base = actions.item(localI);
                NodeList elementsActions = base.getChildNodes();
                String urlPattern = recupererContenuNoeud(elementsActions, "url-pattern");
                String classPattern = recupererContenuNoeud(elementsActions, "form-name");
                correspondanceActionForm.put(urlPattern, classPattern);
            }
        } catch (DOMException e) {
            LOGGER.error(e);
        }
        return correspondanceActionForm;
    }
}

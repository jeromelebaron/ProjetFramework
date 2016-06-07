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

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.afcepf.atod26.framework.api.Action;

/**
 * Description de la classe
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class FactoryConfig {

    /**
     * Le chemin du fichier de configuration.
     */
    private static String pathFichier;
    /**
     * Pour récupérer le chemin du fichier au chargement de l'application.
     */
    static {
        pathFichier = Thread.currentThread().getContextClassLoader().getResource("config.xml")
                .getPath();
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
    public static Map<String, Action> remplirMap() {
        Map<String, Action> mapping = new HashMap<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(pathFichier);
            Element racine = document.getDocumentElement();
            Map<String, String> lesActions = construireLesActions(racine);
            NodeList forms = racine.getElementsByTagName("form");
            for (int localI = 0; localI < forms.getLength(); localI++) {
                Node base = forms.item(localI);
                NodeList elementsforms = base.getChildNodes();
                String formName = null;
                for (int localI2 = 0; localI2 < elementsforms.getLength(); localI2++) {
                    Node enfant = elementsforms.item(localI2);
                    if ("form-name".equals(enfant.getNodeName())) {
                        formName = enfant.getTextContent();
                    }
                    if ("form-class".equals(enfant.getNodeName())) {
                        if (lesActions.get(formName) != null) {
                            Action action = (Action) Class.forName(enfant.getTextContent())
                                    .newInstance();
                            mapping.put(lesActions.get(formName), action);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (DOMException e) {
            e.printStackTrace();
        }
        return mapping;
    }

    private static Map<String, String> construireLesActions(Element racine) {
        final Map<String, String> lesActions = new HashMap<>();
        NodeList actions = racine.getElementsByTagName("action");
        for (int localI = 0; localI < actions.getLength(); localI++) {
            Node base = actions.item(localI);
            NodeList elementsActions = base.getChildNodes();
            String urlPattern = null;
            String formName = null;
            for (int localI2 = 0; localI2 < elementsActions.getLength(); localI2++) {
                Node enfant = elementsActions.item(localI2);
                if ("url-pattern".equals(enfant.getNodeName())) {
                    urlPattern = enfant.getTextContent();
                }
                if ("form-name".equals(enfant.getNodeName())) {
                    formName = enfant.getTextContent();
                }
            }
            lesActions.put(formName, urlPattern);
        }
        return lesActions;
    }

}

/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.test;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.afcepf.atod26.framework.api.Action;
import fr.afcepf.atod26.framework.api.ActionForm;
import fr.afcepf.atod26.framework.impl.FactoryConfig;

/**
 * Description de la classe
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class XMLTest {

    /**
     * @param args
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException,
            IOException {
        String fichier = Thread.currentThread().getContextClassLoader().getResource("config.xml")
                .getPath();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(fichier);

        Element racine = document.getDocumentElement();
        NodeList actions = racine.getElementsByTagName("action");
        for (int localI = 0; localI < actions.getLength(); localI++) {
            Node base = actions.item(localI);
            NodeList elements = base.getChildNodes();
            for (int localI2 = 0; localI2 < elements.getLength(); localI2++) {
                Node enfant = elements.item(localI2);
                if ("url-pattern".equals(enfant.getNodeName())) {
                    System.out.println(enfant.getTextContent());
                }
            }
        }
        Map<String, Action> test = FactoryConfig.remplirMapAction();
        System.out.println(test);
        Map<String, ActionForm> testForm = FactoryConfig.remplirMapForm();
        System.out.println(testForm);
        Map<String, String> testMapping = FactoryConfig.remplirMap();
        System.out.println(testMapping);
    }

}

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

import fr.afcepf.atod26.framework.api.IAction;
import fr.afcepf.atod26.framework.impl.FactoryXMLConfig;
import fr.afcepf.atod26.framework.impl.entity.ActionXML;
import fr.afcepf.atod26.framework.impl.entity.FormXML;
import fr.afcepf.atod26.framework.servlets.actions.ConnexionAction;

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
        FactoryXMLConfig.remplirMapBeans();
        FactoryXMLConfig config = FactoryXMLConfig.getInstance();
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
        Map<String, ActionXML> test = config.remplirMapAction();
        System.out.println(test);
        Map<String, FormXML> testForm = config.remplirMapForm();
        System.out.println(testForm);
        IAction localAction = new ConnexionAction();
        System.out.println(localAction.getClass().getName());
       
    }

}

/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.test;

import java.util.HashMap;
import java.util.Map;

import fr.afcepf.atod26.framework.api.IActionForm;
import fr.afcepf.atod26.framework.formbeans.ActionUnForm;
import fr.afcepf.atod26.framework.impl.MyBeanPopulate;

/**
 * Description de la classe
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class IntrospectionTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        MyBeanPopulate localBeanPopulate = new MyBeanPopulate();
        IActionForm actionUn = new ActionUnForm();
        Map<String, String> mapTest = new HashMap<>();
        mapTest.put("nom", "");
        mapTest.put("age", null);
        localBeanPopulate.populateBean(actionUn, mapTest);
        System.out.println(actionUn.validateForm());
    }

}

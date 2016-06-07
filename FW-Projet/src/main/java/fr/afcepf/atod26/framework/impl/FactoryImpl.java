/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl;

import java.util.HashMap;
import java.util.Map;

import fr.afcepf.atod26.framework.api.Action;
import fr.afcepf.atod26.framework.servlets.ActionDeux;
import fr.afcepf.atod26.framework.servlets.ActionUn;

/**
 * Description de la classe
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class FactoryImpl {

    private static Map<String, Action> mapping;

    static {
        mapping = new HashMap<String, Action>();
        mapping.put("action1", new ActionUn());
        mapping.put("action2", new ActionDeux());
    }

    private FactoryImpl() {
    }

    public static Action fabriqueAction(String paramPath) {
        return mapping.get(paramPath);
    }

}

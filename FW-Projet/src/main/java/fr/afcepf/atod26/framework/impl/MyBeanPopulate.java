/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import fr.afcepf.atod26.framework.api.ISetParametre;
import fr.afcepf.atod26.framework.impl.param.SetParametreInteger;
import fr.afcepf.atod26.framework.impl.param.SetParametreString;

/**
 * Pour peupler un bean de formulaire par instrospection.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class MyBeanPopulate {

    /**
     * Pour faire du log.
     */
    private static final Logger LOGGER = Logger.getLogger(MyBeanPopulate.class);
    /**
     * La map pour mapper les paramètre en fonction de leur type.
     */
    private Map<Class<?>, ISetParametre> mapClasseParam;

    /**
     * La Constructeur.
     */
    public MyBeanPopulate() {
        mapClasseParam = new HashMap<>();
        mapClasseParam.put(String.class, new SetParametreString());
        mapClasseParam.put(Integer.class, new SetParametreInteger());
    }

    /**
     * Pour peupler un bean avec les paramètres de la requete.
     * @param paramObject l'objet pour lequel setter les paramètres.
     * @param paramMapParametresPage les paramètres de la requete à setter.
     */
    public void populateBean(Object paramObject, Map<String, String> paramMapParametresPage) {
        Class<? extends Object> c = paramObject.getClass();
        final Field[] lesAttributs = c.getDeclaredFields();
        for (Field localField : lesAttributs) {
            if (paramMapParametresPage.containsKey(localField.getName())) {
                ISetParametre localISetParametre = mapClasseParam.get(localField.getType());
                try {
                    localISetParametre.setParametre(localField, paramObject,
                            paramMapParametresPage.get(localField.getName()));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    LOGGER.error("Erreur lors du peuplement du bean", e);
                }
            }
        }
    }

}

/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl;

import java.util.Map;

import fr.afcepf.atod26.framework.api.Action;

/**
 * Factory pour construire une instance d'{@link Action}.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class FactoryImpl {

    /**
     * Le mapping de la correspondance entre url et classe {@link Action} concernée.
     */
    private static Map<String, Action> mapping;
    /**
     * Chargement de la map au démarrage de l'application
     */
    static {
        mapping = FactoryConfig.remplirMap();
    }

    /**
     * Constructeur privé.
     */
    private FactoryImpl() {
    }

    /**
     * Pour récupérer une classe de type {@link Action} en fonction d'un url pattern.
     * @param paramPath le pattern pour lequel récupérer la classe.
     * @return l'instance d'{@link Action} correspondante.
     */
    public static Action fabriqueAction(String paramPath) {
        return mapping.get(paramPath);
    }

}

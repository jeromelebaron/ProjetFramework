/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl;

import java.util.Map;

import fr.afcepf.atod26.framework.api.Action;
import fr.afcepf.atod26.framework.api.ActionForm;

/**
 * Factory pour construire une instance d'{@link Action}.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class FactoryImpl {

    /**
     * La map qui contient la correspondance entre une {@link Action} et son {@link ActionForm}.
     */
    private static Map<String, String> mapping;
    /**
     * Le mapping de la correspondance entre url et classe {@link Action} concernée.
     */
    private static Map<String, Action> mappingAction;
    /**
     * Le mapping de la correspondance entre et {@link ActionForm}.
     */
    private static Map<String, ActionForm> mappingActionForm;
    /**
     * Chargement de la map au démarrage de l'application
     */
    static {
        mapping = FactoryConfig.remplirMap();
        mappingAction = FactoryConfig.remplirMapAction();
        mappingActionForm = FactoryConfig.remplirMapForm();
    }

    /**
     * Constructeur privé.
     */
    private FactoryImpl() {
    }

    /**
     * Pour récupérer une classe de type {@link Action} en fonction d'un url-pattern.
     * @param paramPath le pattern pour lequel récupérer la classe.
     * @return l'instance d'{@link Action} correspondante.
     */
    public static Action fabriqueAction(String paramPath) {
        return mappingAction.get(paramPath);
    }

    /**
     * Pour récupérer une classe de type {@link ActionForm} en fonction d'un form-name.
     * @param paramActionForm le form-name pour lequel récupérer la classe.
     * @return l'instance de {@link ActionForm} correspondante.
     */
    public static ActionForm fabriqueActionForm(String paramActionForm) {
        return mappingActionForm.get(paramActionForm);
    }

    /**
     * Pour avoir la correspondance entre l'url-pattern et le form-name.
     * @return la map avec ces informations.
     */
    public static Map<String, String> fabriqueCorrespondanceActionEtForm() {
        return mapping;
    }

}

/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl;

import java.util.Map;

import fr.afcepf.atod26.framework.api.Action;
import fr.afcepf.atod26.framework.api.ActionForm;
import fr.afcepf.atod26.framework.api.IConfig;

/**
 * Factory pour récupérer les éléments du fichier XML.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class FactoryImpl {

    /**
     * Singleton
     */
    private static FactoryImpl factoryImpl;
    /**
     * L'instance nécessaire pour récupérer la configuration du framework.
     */
    private static IConfig config;
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
     * La map qui contient la correspondance entre une {@link Action} et sa vue.
     */
    private static Map<String, String> mappingView;
    /**
     * Chargement de la map au démarrage de l'application
     */
    static {
        if (factoryImpl == null) {
            factoryImpl = new FactoryImpl();
        }
        config = FactoryXMLConfig.getInstance();
        mapping = config.remplirMap("action", "url-pattern", "form-name");
        mappingAction = config.remplirMapAction();
        mappingActionForm = config.remplirMapForm();
        mappingView = config.remplirMap("action", "url-pattern", "from-view");
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
     * @param paramPath
     * @return la map avec ces informations.
     */
    public static String fabriqueCorrespondanceActionEtForm(String paramPath) {
        return mapping.get(paramPath);
    }

    /**
     * Pour avoir la vue correspondant à l'url-pattern.
     * @param paramPath l'url-pattern.
     * @return le chemin de la vue correspondante.
     */
    public static String getView(String paramPath) {
        return mappingView.get(paramPath);
    }

}

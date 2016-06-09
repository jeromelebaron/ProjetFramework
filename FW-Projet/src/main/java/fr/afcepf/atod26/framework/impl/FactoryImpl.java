/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import fr.afcepf.atod26.framework.api.IAction;
import fr.afcepf.atod26.framework.api.IActionForm;
import fr.afcepf.atod26.framework.api.IConfig;
import fr.afcepf.atod26.framework.api.IFactory;

/**
 * Factory pour récupérer les éléments du fichier XML.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class FactoryImpl implements IFactory {

    /**
     * Pour faire du log.
     */
    private static final Logger LOGGER = Logger.getLogger(FactoryImpl.class);
    /**
     * Singleton.
     */
    private static FactoryImpl factoryImpl;
    /**
     * L'instance nécessaire pour récupérer la configuration du framework.
     */
    private IConfig config;
    /**
     * La map qui contient la correspondance entre une {@link IAction} et son {@link IActionForm}.
     */
    private Map<String, String> mapping;
    /**
     * Le mapping de la correspondance entre url et classe {@link IAction} concernée.
     */
    private Map<String, IAction> mappingAction;
    /**
     * Le mapping de la correspondance entre url et {@link IActionForm}.
     */
    private Map<String, IActionForm> mappingActionForm;
    /**
     * La map qui contient la correspondance entre une {@link IAction} et sa vue.
     */
    private Map<String, String> mappingView;
    /**
     * La map qui contient les <code>forward</code> des actions.
     */
    private Map<String, Map<String, String>> mappingForward;

    /**
     * Constructeur.
     */
    private FactoryImpl() {
        // EMPTY
    }

    /**
     * Pour récupérer l'instance du singleton.
     * @return le singleton.
     */
    public static FactoryImpl getInstance() {
        return factoryImpl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IAction fabriqueAction(String paramPath) {
        LOGGER.debug("Méthode fabriqueAction");
        if (mappingAction == null) {
            mappingAction = config.remplirMapAction();
        }
        return mappingAction.get(paramPath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IActionForm fabriqueActionForm(String paramActionForm) {
        LOGGER.debug("Méthode fabriqueActionForm");
        if (mappingActionForm == null) {
            mappingActionForm = config.remplirMapForm();
        }
        return mappingActionForm.get(paramActionForm);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String fabriqueCorrespondanceActionEtForm(String paramPath) {
        LOGGER.debug("Méthode fabriqueCorrespondanceActionEtForm");
        if (mapping == null) {
            mapping = config.remplirMap("action", "url-pattern", "form-name");
        }
        return mapping.get(paramPath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getView(String paramPath) {
        LOGGER.debug("Méthode getView");
        if (mappingView == null) {
            mappingView = config.remplirMap("action", "url-pattern", "from-view");
        }
        return mappingView.get(paramPath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getForward(String paramActionName, String paramName) {
        LOGGER.debug("Méthode getForward");
        if (mappingForward == null) {
            mappingForward = config.remplirMapForward();
        }
        return mappingForward.get(paramActionName).get(paramName);
    }

    /**
     * Accesseur en écriture du champ <code>factoryImpl</code>.
     * @param paramFactoryImpl la valeur à écrire dans <code>factoryImpl</code>.
     */
    public static void setFactoryImpl(FactoryImpl paramFactoryImpl) {
        factoryImpl = paramFactoryImpl;
    }

}

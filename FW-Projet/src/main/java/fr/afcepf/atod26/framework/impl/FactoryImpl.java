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
import fr.afcepf.atod26.framework.impl.entity.ActionXML;
import fr.afcepf.atod26.framework.impl.entity.FormXML;
import fr.afcepf.atod26.framework.impl.entity.ForwardXML;

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
     * Le mapping de la correspondance entre url et classe {@link IAction} concernée.
     */
    private Map<String, ActionXML> mappingAction;
    /**
     * Le mapping de la correspondance entre url et {@link IActionForm}.
     */
    private Map<String, FormXML> mappingActionForm;

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
    public ActionXML fabriqueAction(String paramPath) {
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
        return mappingActionForm.get(paramActionForm).getActionForm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getForward(String paramActionName, String paramName) {
        LOGGER.debug("Méthode getForward");
        if (mappingAction == null) {
            mappingAction = config.remplirMapAction();
        }
        String forward = null;
        for (ForwardXML localForwardXML : mappingAction.get(paramActionName).getForwardXMLs()) {
            if (paramName.equals(localForwardXML.getName())) {
                forward = localForwardXML.getPath();
            }
        }
        return forward;
    }

    /**
     * Accesseur en écriture du champ <code>factoryImpl</code>.
     * @param paramFactoryImpl la valeur à écrire dans <code>factoryImpl</code>.
     */
    public static void setFactoryImpl(FactoryImpl paramFactoryImpl) {
        factoryImpl = paramFactoryImpl;
    }

}

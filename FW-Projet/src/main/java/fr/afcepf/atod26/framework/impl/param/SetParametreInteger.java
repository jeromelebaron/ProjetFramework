/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl.param;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import fr.afcepf.atod26.framework.api.ISetParametre;

/**
 * Pour peupler en champs si c'est l'attribut est de type <code>Integer</code>.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class SetParametreInteger implements ISetParametre {

    /**
     * Pour faire du log.
     */
    private static final Logger LOGGER = Logger.getLogger(SetParametreInteger.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParametre(Field paramField, Object paramObject, String paramNameField)
            throws IllegalAccessException {
        paramField.setAccessible(true);
        LOGGER.debug("Méthode setParametre");
        Integer entier = null;
        if (paramNameField != null && !paramNameField.isEmpty()) {
            try {
                entier = Integer.parseInt(paramNameField);
            } catch (NumberFormatException localE) {
                LOGGER.error("Erreur de parsing", localE);
            }
        }
        paramField.set(paramObject, entier);
    }

}

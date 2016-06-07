/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl.param;

import java.lang.reflect.Field;

import fr.afcepf.atod26.framework.api.ISetParametre;

/**
 * Description de la classe
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class SetParametreInteger implements ISetParametre {

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParametre(Field paramField, Object paramObject, String paramNameField)
            throws IllegalArgumentException, IllegalAccessException {
        paramField.setAccessible(true);
        Integer entier = null;
        if (!paramNameField.isEmpty()) {
            entier = Integer.parseInt(paramNameField);
        }
        paramField.set(paramObject, entier);
    }

}

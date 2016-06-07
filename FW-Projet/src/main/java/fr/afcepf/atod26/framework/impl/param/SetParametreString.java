/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl.param;

import java.lang.reflect.Field;

import fr.afcepf.atod26.framework.api.ISetParametre;

/**
 * Pour peupler en champs si c'est l'attribut est de type <code>String</code>.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class SetParametreString implements ISetParametre {

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParametre(Field paramField, Object paramObject, String nameField)
            throws IllegalAccessException {
        paramField.setAccessible(true);
        paramField.set(paramObject, nameField);
    }

}

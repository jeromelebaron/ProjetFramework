/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl.param;

import java.lang.reflect.Field;

import fr.afcepf.atod26.framework.api.ISetParametre;

/**
 * Description de la classe
 * 
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class SetParametreString implements ISetParametre{

    @Override
    public void setParametre(Field paramField, Object paramObject, String nameField)
            throws IllegalArgumentException, IllegalAccessException {
        paramField.setAccessible(true);
        paramField.set(paramObject, nameField);
    }

}


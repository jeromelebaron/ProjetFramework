/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.api;

import java.lang.reflect.Field;

/**
 * Description de la classe
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public interface ISetParametre {

    /**
     * Pour setter un paramètre.
     * @param paramField
     * @param paramObject
     * @param nameField
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    void setParametre(Field paramField, Object paramObject, String nameField)
            throws IllegalArgumentException, IllegalAccessException;
}

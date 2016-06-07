/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.api;

import java.lang.reflect.Field;

/**
 * Interface qui permet de setter un attribut par introspection en fonction de sont type.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public interface ISetParametre {

    /**
     * Pour setter un paramètre.
     * @param paramField l'attribut à set.
     * @param paramObject l'objet pour lequel l'attribut est à set.
     * @param nameField le nom de l'attribut.
     * @throws IllegalArgumentException au cas ou.
     * @throws IllegalAccessException au cas ou.
     */
    void setParametre(Field paramField, Object paramObject, String nameField)
            throws IllegalAccessException;
}

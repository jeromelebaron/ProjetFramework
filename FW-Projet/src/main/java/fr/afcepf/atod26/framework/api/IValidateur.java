/**
 * Créé le 8 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.api;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Pour valider les annotations.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public interface IValidateur {

    /**
     * Pour valider si un champs respecte les condictions de son annotation.
     * @param paramFieldAValider le champs à valider.
     * @return l'erreur si il y a lieu.
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    Boolean valider(Object objetDuChamps, Field paramFieldAValider, Map<String, String> erreurs)
            throws IllegalAccessException;
}

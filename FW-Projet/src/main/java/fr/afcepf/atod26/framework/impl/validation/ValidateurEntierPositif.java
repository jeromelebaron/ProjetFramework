/*
 * Créé le 8 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl.validation;

import java.lang.reflect.Field;
import java.util.Map;

import fr.afcepf.atod26.framework.api.IValidateur;

/**
 * Description de la classe
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class ValidateurEntierPositif implements IValidateur {

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean valider(Object paramObjetDuChamps, Field paramFieldAValider,
            Map<String, String> erreurs) throws IllegalAccessException {
        Boolean isValide = false;
        final Object localFieldValue = paramFieldAValider.get(paramObjetDuChamps);
        if ((Integer) localFieldValue != null && ((Integer) localFieldValue).intValue() <= 0) {
            erreurs.put(paramFieldAValider.getName(), "Ce champs être positif");
            isValide = true;
        }
        return isValide;
    }

}

/*
 * Créé le 8 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl.validation;

import java.lang.reflect.Field;
import java.util.Map;

import fr.afcepf.atod26.framework.annotations.Obligatoire;
import fr.afcepf.atod26.framework.api.IValidateur;

/**
 * Description de la classe
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class ValidateurObligatoire implements IValidateur {

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean valider(Object objectDuChamps, Field paramFieldAValider,
            Map<String, String> erreurs) throws IllegalAccessException {
        Boolean isValide = false;
        final Obligatoire annotObligatoire = paramFieldAValider.getAnnotation(Obligatoire.class);
        if (annotObligatoire != null) {
            final Object localFieldValue = paramFieldAValider.get(objectDuChamps);
            if (localFieldValue == null) {
                erreurs.put(paramFieldAValider.getName(), "Ce champs est obligatoire");
            }
        }
        return isValide;
    }

}

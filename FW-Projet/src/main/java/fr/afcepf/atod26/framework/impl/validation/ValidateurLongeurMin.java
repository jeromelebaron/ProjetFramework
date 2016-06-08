/*
 * Créé le 8 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl.validation;

import java.lang.reflect.Field;
import java.util.Map;

import fr.afcepf.atod26.framework.annotations.LongueurMin;
import fr.afcepf.atod26.framework.api.IValidateur;

/**
 * Pour la validation de l'annotation {@link LongeurMin};
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class ValidateurLongeurMin implements IValidateur {

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean valider(Object paramObjetDuChamps, Field paramFieldAValider,
            Map<String, String> erreurs) throws IllegalAccessException {
        Boolean validation = false;
        int longueurMin = paramFieldAValider.getAnnotation(LongueurMin.class).longueurMin();
        final Object localFieldValue = paramFieldAValider.get(paramObjetDuChamps);
        if (((String) localFieldValue).length() <= longueurMin) {
            erreurs.put(paramFieldAValider.getName(), "Ce champs doit avoir un longeur minimum de "
                    + longueurMin + " caractères.");
            validation = true;
        }
        return validation;
    }
}

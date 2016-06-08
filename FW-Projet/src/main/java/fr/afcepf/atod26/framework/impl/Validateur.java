/*
 * Créé le 8 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import fr.afcepf.atod26.framework.annotations.EntierPositif;
import fr.afcepf.atod26.framework.annotations.LongueurMin;
import fr.afcepf.atod26.framework.annotations.Obligatoire;
import fr.afcepf.atod26.framework.api.IValidateur;
import fr.afcepf.atod26.framework.impl.validation.ValidateurEntierPositif;
import fr.afcepf.atod26.framework.impl.validation.ValidateurLongeurMin;
import fr.afcepf.atod26.framework.impl.validation.ValidateurObligatoire;

/**
 * Pour valider les éléments avec des annotations.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class Validateur {

    private static Map<Class<?>, IValidateur> mapValidateur;

    static {
        mapValidateur = new HashMap<>();
        mapValidateur.put(Obligatoire.class, new ValidateurObligatoire());
        mapValidateur.put(LongueurMin.class, new ValidateurLongeurMin());
        mapValidateur.put(EntierPositif.class, new ValidateurEntierPositif());
    }

    /**
     * Constructeur.
     */
    private Validateur() {
    }

    /**
     * Pour valider un objet en fonction de ces annotations.
     * @param paramObjetAValider l'objet pour lequel faire la validation.
     * @return une Map avec des erreurs si il y en a.
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Map<String, String> validerFormulaire(Object paramObjetAValider)
            throws IllegalAccessException {
        Map<String, String> erreurs = new HashMap<>();
        final Field[] attributsObjet = paramObjetAValider.getClass().getDeclaredFields();
        for (Field localField : attributsObjet) {
            localField.setAccessible(true);
            final Obligatoire annotObligatoire = localField.getAnnotation(Obligatoire.class);
            Boolean isObligatoire = false;
            if (annotObligatoire != null) {
                isObligatoire = mapValidateur.get(Obligatoire.class).valider(paramObjetAValider,
                        localField, erreurs);
            }
            if (!isObligatoire) {
                final LongueurMin annotLongueurMin = localField.getAnnotation(LongueurMin.class);
                if (annotLongueurMin != null) {
                    mapValidateur.get(LongueurMin.class).valider(paramObjetAValider, localField,
                            erreurs);
                }
                final EntierPositif annotEntierPositif = localField
                        .getAnnotation(EntierPositif.class);
                if (annotEntierPositif != null) {
                    mapValidateur.get(EntierPositif.class).valider(paramObjetAValider, localField,
                            erreurs);
                }
            }

        }
        return erreurs;
    }

}

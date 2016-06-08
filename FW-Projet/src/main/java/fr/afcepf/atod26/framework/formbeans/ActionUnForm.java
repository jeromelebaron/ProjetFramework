/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.formbeans;

import java.util.HashMap;
import java.util.Map;

import fr.afcepf.atod26.framework.annotations.EntierPositif;
import fr.afcepf.atod26.framework.annotations.LongueurMin;
import fr.afcepf.atod26.framework.annotations.Obligatoire;
import fr.afcepf.atod26.framework.api.IActionForm;
import fr.afcepf.atod26.framework.impl.Validateur;

/**
 * La classe qui correspond au formulaire lié au parttern actionun.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class ActionUnForm implements IActionForm {

    /**
     * Le nom de la personne.
     */
    @Obligatoire
    @LongueurMin(longueurMin = 2)
    private String nom;
    /**
     * L'age de la personne
     */
    @Obligatoire
    @EntierPositif
    private Integer age;

    /**
     * {@inheritDoc},
     */
    @Override
    public Map<String, String> validateForm() {
        Map<String, String> erreurs = new HashMap<>();
        try {
            erreurs.putAll(Validateur.validerFormulaire(this));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return erreurs;
    }

}

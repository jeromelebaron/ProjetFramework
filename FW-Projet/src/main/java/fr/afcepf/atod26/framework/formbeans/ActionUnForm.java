/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.formbeans;

import java.util.HashMap;
import java.util.Map;

import fr.afcepf.atod26.framework.api.IActionForm;

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
    private String nom;
    /**
     * L'age de la personne
     */
    private Integer age;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> validateForm() {
        Map<String, String> erreurs = new HashMap<>();
        if (nom.length() <= 0) {
            erreurs.put("nom", "Vous devez rentrer au moins un caractère pour votre nom");
        }
        if (age == null || age.intValue() <= 0) {
            erreurs.put("age", "L'age ne peut etre négatif");
        }
        return erreurs;
    }

}

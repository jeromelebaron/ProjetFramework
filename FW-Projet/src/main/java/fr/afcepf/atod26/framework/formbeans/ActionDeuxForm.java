/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.formbeans;

import java.util.HashMap;
import java.util.Map;

import fr.afcepf.atod26.framework.api.IActionForm;

/**
 * Description de la classe
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class ActionDeuxForm implements IActionForm {

    private String prenom;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> validateForm() {
        Map<String, String> erreurs = new HashMap<>();
        if ("jerome".equals(prenom)) {
            erreurs.put("prenom", "Prénom de merde");
        }
        return erreurs;
    }

}

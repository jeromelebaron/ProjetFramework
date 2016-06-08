/*
 * Créé le 8 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.formbeans;

import java.util.HashMap;
import java.util.Map;

import fr.afcepf.atod26.framework.api.IActionForm;

/**
 * Pour la validation du formulaire du profil.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class ProfilActionForm implements IActionForm {

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> validateForm() {
        Map<String, String> erreurs = new HashMap<>();
        return erreurs;
    }

}

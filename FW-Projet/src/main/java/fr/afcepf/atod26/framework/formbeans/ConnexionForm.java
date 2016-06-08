/*
 * Créé le 8 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.formbeans;

import java.util.HashMap;
import java.util.Map;

import fr.afcepf.atod26.framework.api.IActionForm;
import fr.afcepf.atod26.framework.impl.Validateur;

/**
 * Description de la classe
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class ConnexionForm implements IActionForm {

    private String nom;

    private String motDePasse;

    /**
     * {@inheritDoc}
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

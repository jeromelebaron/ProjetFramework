/*
 * Créé le 8 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.formbeans;

import java.util.HashMap;
import java.util.Map;

import fr.afcepf.atod26.framework.annotations.Obligatoire;
import fr.afcepf.atod26.framework.api.IActionForm;
import fr.afcepf.atod26.framework.impl.validation.Validateur;
import fr.afcepf.atod26.framework.security.LoginBean;

/**
 * Pour la validation du formulaire de connexion.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class ConnexionActionForm implements IActionForm {

    /**
     * Le login de connexion.
     */
    @Obligatoire
    private String login;

    /**
     * Le mot de passe.
     */
    @Obligatoire
    private String password;

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
        LoginBean loginBean = new LoginBean();
        loginBean.connexion(login, password);
        if (!loginBean.isLogged()) {
            erreurs.put("authentification", "Login ou mot de passe erroné.");
        }
        return erreurs;
    }

}

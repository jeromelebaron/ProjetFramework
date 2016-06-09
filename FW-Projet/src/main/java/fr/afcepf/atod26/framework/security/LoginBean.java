/*
 * Créé le 8 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Contient tous les éléments nécessaires à la connexion.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class LoginBean implements Serializable {

    /**
     * Sérialisation.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Le utilisateurs.
     */
    private static final Map<String, String> USERS;
    /**
     * Savoir si on est loggé ou pas.
     */
    private boolean isLogged = false;

    /**
     * Pour charger les utilisateurs.
     */
    static {
        USERS = new HashMap<>();
        USERS.put("jerome", "dopler");
    }

    /**
     * Constructeur.
     */
    public LoginBean() {
        // EMPTY
    }

    /**
     * Pour se connecter.
     * @param paramLogin le login à vérifier.
     * @param paramMotDePass le mot de passe à vérifier.
     */
    public void connexion(String paramLogin, String paramMotDePass) {
        if (USERS.get(paramLogin) != null && paramMotDePass.equals(USERS.get(paramLogin))) {
            isLogged = true;
        }
    }

    /**
     * Accesseur en lecture du champ <code>isLogged</code>.
     * @return le champ <code>isLogged</code>.
     */
    public boolean isLogged() {
        return isLogged;
    }

    /**
     * Accesseur en écriture du champ <code>isLogged</code>.
     * @param paramIsLogged la valeur à écrire dans <code>isLogged</code>.
     */
    public void setLogged(boolean paramIsLogged) {
        isLogged = paramIsLogged;
    }

}

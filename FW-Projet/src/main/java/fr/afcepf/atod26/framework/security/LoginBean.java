/*
 * Créé le 8 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Description de la classe
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class LoginBean implements Serializable {

    /**
     * Sérialisation.
     */
    private static final long serialVersionUID = 1L;

    private static final Map<String, String> USERS;

    private boolean isLogged = false;

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
     * @param paramLogin
     * @param paramMotDePass
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

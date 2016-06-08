/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface pour la redirection des implémentations.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public interface IAction {

    /**
     * Pour éxécuter les actions.
     * @param request la requete http.
     * @param response la reponse http.
     * @return l'url de la page sur laquelle etre redirigé.
     */
    String execute(HttpServletRequest request, HttpServletResponse response);

}

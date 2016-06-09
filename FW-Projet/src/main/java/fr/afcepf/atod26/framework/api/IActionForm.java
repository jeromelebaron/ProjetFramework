/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.api;

import java.util.Map;

/**
 * Pour la validation des formulaires.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public interface IActionForm {

    /**
     * Pour savoir si le formulaire est valide ou pas.
     * @return une Map qui contient des erreurs si il y en a, une map vide sinon.
     */
    Map<String, String> validateForm();
}

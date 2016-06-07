/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.api;

import java.util.Map;

/**
 * L'interface pour la validation des formulaires.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public interface ActionForm {

    /**
     * Pour savoir si le formulaire est valide ou pas.
     * @return <code>true</code> si c'est bon, <code>false</code> sinon.
     */
    Map<String, String> validateForm();
}

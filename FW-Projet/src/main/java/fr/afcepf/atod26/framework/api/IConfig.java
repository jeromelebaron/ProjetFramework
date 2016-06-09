/*
 * Créé le 8 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.api;

import java.util.Map;

import fr.afcepf.atod26.framework.impl.entity.ActionXML;
import fr.afcepf.atod26.framework.impl.entity.FormXML;

/**
 * Les méthodes nécessaires à la configuration du framework.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public interface IConfig {

    /**
     * Pour construire la Map avec la correspondance entre url pattern et classe correspondante.
     * @return la map remplie.
     */
    Map<String, ActionXML> remplirMapAction();

    /**
     * Pour remplir la map des classes qui implémentent {@link IActionForm}.
     * @return la map avec en clé le form-name et en valeur l'instance de la classe correspondante.
     */
    Map<String, FormXML> remplirMapForm();

}

/*
 * Créé le 8 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.api;

import java.util.Map;

/**
 * Contient les méthodes nécessaires à la configuration du framework.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public interface IConfig {

    /**
     * Pour construire la Map avec la correspondance entre url pattern et classe correspondante.
     * @return la map remplie.
     */
    Map<String, IAction> remplirMapAction();

    /**
     * Pour remplir la map des classes qui implémentent {@link IActionForm}
     * @return la map avec en clé le form-name et en valeur l'instance de la classe correspondante.
     */
    Map<String, IActionForm> remplirMapForm();

    /**
     * La map avec la correspondance entre la cle et la valeur du tag parent précisé.
     * @param paramTagParent le nom du tag.
     * @param paramCle la balise enfant de ce tag dont le contenu servira de clef.
     * @param paramValeur la balise enfant de ce tage dont le contenu servira de valeur.
     * @return une map avec cle et valeur passée en paramètre.
     */
    Map<String, String> remplirMap(String paramTagParent, String paramCle, String paramValeur);
}

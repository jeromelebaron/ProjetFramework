/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.revision.service;

import java.util.ArrayList;
import java.util.List;

import fr.afcepf.atod26.framework.revision.entity.Personne;

/**
 * Description de la classe
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class PersonneService {

    private List<Personne> personnes;

    /**
     * Constructeur.
     */
    public PersonneService() {
        personnes = new ArrayList<>();
    }

    /**
     * Accesseur en lecture du champ <code>personnes</code>.
     * @return le champ <code>personnes</code>.
     */
    public List<Personne> getPersonnes() {
        return personnes;
    }

    /**
     * Accesseur en écriture du champ <code>personnes</code>.
     * @param paramPersonnes la valeur à écrire dans <code>personnes</code>.
     */
    public void setPersonnes(List<Personne> paramPersonnes) {
        personnes = paramPersonnes;
    }

}

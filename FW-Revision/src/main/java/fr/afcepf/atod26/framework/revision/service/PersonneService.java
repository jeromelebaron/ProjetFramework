/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.revision.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.afcepf.atod26.framework.revision.entity.Personne;

/**
 * Description de la classe
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class PersonneService implements Serializable {

    /**
     * Sérialisation
     */
    private static final long serialVersionUID = 1L;

    private List<Personne> personnes;

    /**
     * Constructeur.
     */
    public PersonneService() {
        personnes = new ArrayList<>();
        personnes.add(new Personne("n1", "p1"));
        personnes.add(new Personne("n2", "p2"));
        personnes.add(new Personne("n3", "p3"));
    }

    public List<Personne> getAll() {
        return personnes;
    }

}

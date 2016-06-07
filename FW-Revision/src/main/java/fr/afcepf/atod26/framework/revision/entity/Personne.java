/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.revision.entity;

/**
 * Description de la classe
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class Personne {

    private String nom;

    private String prenom;

    /**
     * Constructeur.
     */
    public Personne() {
        super();
    }

    /**
     * Constructeur.
     * @param paramNom
     * @param paramPrenom
     */
    public Personne(String paramNom, String paramPrenom) {
        super();
        nom = paramNom;
        prenom = paramPrenom;
    }

    /**
     * Accesseur en lecture du champ <code>nom</code>.
     * @return le champ <code>nom</code>.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Accesseur en écriture du champ <code>nom</code>.
     * @param paramNom la valeur à écrire dans <code>nom</code>.
     */
    public void setNom(String paramNom) {
        nom = paramNom;
    }

    /**
     * Accesseur en lecture du champ <code>prenom</code>.
     * @return le champ <code>prenom</code>.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Accesseur en écriture du champ <code>prenom</code>.
     * @param paramPrenom la valeur à écrire dans <code>prenom</code>.
     */
    public void setPrenom(String paramPrenom) {
        prenom = paramPrenom;
    }

    @Override
    public String toString() {
        return "Personne [nom=" + nom + ", prenom=" + prenom + "]";
    }

}

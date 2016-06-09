/*
 * Créé le 9 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl.entity;

/**
 * Description de la classe
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class ForwardXML {

    private String name;

    private String path;

    /**
     * Constructeur.
     */
    public ForwardXML() {
        // EMPTY
    }

    /**
     * Constructeur.
     * @param paramName
     * @param paramPath
     */
    public ForwardXML(String paramName, String paramPath) {
        super();
        name = paramName;
        path = paramPath;
    }

    /**
     * Accesseur en lecture du champ <code>name</code>.
     * @return le champ <code>name</code>.
     */
    public String getName() {
        return name;
    }

    /**
     * Accesseur en écriture du champ <code>name</code>.
     * @param paramName la valeur à écrire dans <code>name</code>.
     */
    public void setName(String paramName) {
        name = paramName;
    }

    /**
     * Accesseur en lecture du champ <code>path</code>.
     * @return le champ <code>path</code>.
     */
    public String getPath() {
        return path;
    }

    /**
     * Accesseur en écriture du champ <code>path</code>.
     * @param paramPath la valeur à écrire dans <code>path</code>.
     */
    public void setPath(String paramPath) {
        path = paramPath;
    }

}

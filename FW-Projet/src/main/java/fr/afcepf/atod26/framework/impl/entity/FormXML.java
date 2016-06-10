/*
 * Créé le 9 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl.entity;

import fr.afcepf.atod26.framework.api.IActionForm;

/**
 * L'entité qui correspond à une balise <code>form</code> du xml de configuration.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class FormXML {

    /**
     * L'instance liée à la balise.
     */
    private IActionForm actionForm;
    /**
     * Le nom du formulaire.
     */
    private String formName;

    /**
     * Constructeur.
     */
    public FormXML() {
        // EMPTY
    }

    /**
     * Accesseur en lecture du champ <code>actionForm</code>.
     * @return le champ <code>actionForm</code>.
     */
    public IActionForm getActionForm() {
        return actionForm;
    }

    /**
     * Accesseur en écriture du champ <code>actionForm</code>.
     * @param paramActionForm la valeur à écrire dans <code>actionForm</code>.
     */
    public void setActionForm(IActionForm paramActionForm) {
        actionForm = paramActionForm;
    }

    /**
     * Accesseur en lecture du champ <code>formName</code>.
     * @return le champ <code>formName</code>.
     */
    public String getFormName() {
        return formName;
    }

    /**
     * Accesseur en écriture du champ <code>formName</code>.
     * @param paramFormName la valeur à écrire dans <code>formName</code>.
     */
    public void setFormName(String paramFormName) {
        formName = paramFormName;
    }

}

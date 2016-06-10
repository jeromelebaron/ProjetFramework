/*
 * Créé le 9 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.impl.entity;

import java.util.List;

import fr.afcepf.atod26.framework.api.IAction;

/**
 * L'entité qui correspond à une balise <code>action</code> du xml de configuration.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class ActionXML {

    /**
     * L'instance {@link IAction}.
     */
    private IAction action;
    /**
     * L'url pattern de la balise.
     */
    private String urlPattern;
    /**
     * Le nom complet de la classe liée à l'{@link IAction}.
     */
    private String formName;
    /**
     * D'ou l'on vient.
     */
    private String fromView;
    /**
     * La liste des chemins.
     */
    private List<ForwardXML> forwardXMLs;

    /**
     * Constructeur.
     */
    public ActionXML() {
        // EMPTY
    }

    /**
     * Accesseur en lecture du champ <code>action</code>.
     * @return le champ <code>action</code>.
     */
    public IAction getAction() {
        return action;
    }

    /**
     * Accesseur en écriture du champ <code>action</code>.
     * @param paramAction la valeur à écrire dans <code>action</code>.
     */
    public void setAction(IAction paramAction) {
        action = paramAction;
    }

    /**
     * Accesseur en lecture du champ <code>urlPattern</code>.
     * @return le champ <code>urlPattern</code>.
     */
    public String getUrlPattern() {
        return urlPattern;
    }

    /**
     * Accesseur en écriture du champ <code>urlPattern</code>.
     * @param paramUrlPattern la valeur à écrire dans <code>urlPattern</code>.
     */
    public void setUrlPattern(String paramUrlPattern) {
        urlPattern = paramUrlPattern;
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

    /**
     * Accesseur en lecture du champ <code>fromView</code>.
     * @return le champ <code>fromView</code>.
     */
    public String getFromView() {
        return fromView;
    }

    /**
     * Accesseur en écriture du champ <code>fromView</code>.
     * @param paramFromView la valeur à écrire dans <code>fromView</code>.
     */
    public void setFromView(String paramFromView) {
        fromView = paramFromView;
    }

    /**
     * Accesseur en lecture du champ <code>forwardXMLs</code>.
     * @return le champ <code>forwardXMLs</code>.
     */
    public List<ForwardXML> getForwardXMLs() {
        return forwardXMLs;
    }

    /**
     * Accesseur en écriture du champ <code>forwardXMLs</code>.
     * @param paramForwardXMLs la valeur à écrire dans <code>forwardXMLs</code>.
     */
    public void setForwardXMLs(List<ForwardXML> paramForwardXMLs) {
        forwardXMLs = paramForwardXMLs;
    }

}

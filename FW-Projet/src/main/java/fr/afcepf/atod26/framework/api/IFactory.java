/*
 * Créé le 8 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.api;


/**
 * Regroupe toutes les éléments à fabriquer.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public interface IFactory {

    /**
     * Pour récupérer une classe de type {@link IAction} en fonction d'un url-pattern.
     * @param paramPath le pattern pour lequel récupérer la classe.
     * @return l'instance d'{@link IAction} correspondante.
     */
    IAction fabriqueAction(String paramPath);

    /**
     * Pour récupérer une classe de type {@link IActionForm} en fonction d'un form-name.
     * @param paramActionForm le form-name pour lequel récupérer la classe.
     * @return l'instance de {@link IActionForm} correspondante.
     */
    IActionForm fabriqueActionForm(String paramActionForm);

    /**
     * Pour avoir la correspondance entre l'url-pattern et le form-name.
     * @param paramPath l'url-pattern.
     * @return la map avec ces informations.
     */
    String fabriqueCorrespondanceActionEtForm(String paramPath);

    /**
     * Pour avoir la vue correspondant à l'url-pattern.
     * @param paramPath l'url-pattern.
     * @return le chemin de la vue correspondante.
     */
    String getView(String paramPath);

    /**
     * Pour avoir la forward d'une action en fonction de la propriété <code>name</code> du fichier
     * de configuration.
     * @param paramActionName le nom de l'action du fichier xml.
     * @param paramName la valeur de la propriété name.
     * @return ou aller.
     */
    String getForward(String paramActionName, String paramName);
}

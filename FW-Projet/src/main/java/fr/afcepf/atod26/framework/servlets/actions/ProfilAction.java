/*
 * Créé le 8 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.servlets.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.afcepf.atod26.framework.api.IAction;

/**
 * Pour la redirection vers la page de profil.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class ProfilAction implements IAction {

    /**
     * Pour faire du log.
     */
    private Logger logger = Logger.getLogger(ProfilAction.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest paramRequest, HttpServletResponse paramResponse) {
        logger.info("je suis dans ProfilAction");
        List<String> artistes = new ArrayList<>();
        artistes.add("Metallica");
        artistes.add("Iron Maiden");
        paramRequest.setAttribute("artistes", artistes);
        return "/secured/profil.jsp";
    }

}

/*
 * Créé le 7 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.afcepf.atod26.framework.api.Action;

/**
 * La classe qui permet d'aller sur path /action1
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class ActionUn implements Action {

    private Logger logger = Logger.getLogger(ActionUn.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest paramRequest, HttpServletResponse paramResponse) {
        logger.info("je suis dans Action1");
        return "actionun.jsp";
    }

}

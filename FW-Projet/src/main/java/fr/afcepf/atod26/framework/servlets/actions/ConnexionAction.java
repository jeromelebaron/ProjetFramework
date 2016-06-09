/*
 * Créé le 8 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.servlets.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.afcepf.atod26.framework.api.IAction;
import fr.afcepf.atod26.framework.impl.FactoryImpl;
import fr.afcepf.atod26.framework.security.LoginBean;

/**
 * Au moment de la connexion.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class ConnexionAction implements IAction {

    /**
     * Pour faire du log.
     */
    private Logger logger = Logger.getLogger(ConnexionAction.class);
    /**
     * Pour récupérer le mapping d'url.
     */
    private FactoryImpl factoryImpl = FactoryImpl.getInstance();

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest paramRequest, HttpServletResponse paramResponse) {
        logger.info("je suis dans ConnexionAction");
        LoginBean loginBean = new LoginBean();
        loginBean.setLogged(true);
        paramRequest.getSession().setAttribute("loginBean", loginBean);
        return factoryImpl.getForward(this.getClass().getName(), "success");
    }

}

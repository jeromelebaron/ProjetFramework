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
import fr.afcepf.atod26.framework.impl.FactoryImpl;

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
    private static final Logger LOGGER = Logger.getLogger(ProfilAction.class);
    /**
     * Pour récupérer le mapping d'url.
     */
    private FactoryImpl factoryImpl = FactoryImpl.getInstance();

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest paramRequest, HttpServletResponse paramResponse) {
        LOGGER.debug("Méthode execute");
        final List<String> artistes = new ArrayList<>();
        artistes.add("Metallica");
        artistes.add("Iron Maiden");
        paramRequest.setAttribute("artistes", artistes);
        return factoryImpl.getForward(this.getClass().getName(), "success");
    }

}

package fr.afcepf.atod26.framework.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.afcepf.atod26.framework.api.IActionForm;
import fr.afcepf.atod26.framework.api.IFactory;
import fr.afcepf.atod26.framework.impl.FactoryImpl;
import fr.afcepf.atod26.framework.impl.FactoryXMLConfig;
import fr.afcepf.atod26.framework.impl.MyBeanPopulate;
import fr.afcepf.atod26.framework.impl.entity.ActionXML;

/**
 * Servlet implementation class ActionServlet
 */
@WebServlet("*.frm")
public class ActionServlet extends HttpServlet {

    /**
     * Pour faire du log.
     */
    private static final Logger LOGGER = Logger.getLogger(ActionServlet.class);
    /**
     * Pour la sérialisation
     */
    private static final long serialVersionUID = 1L;

    private final IFactory factory;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet() {
        super();
        FactoryXMLConfig.remplirMapBeans();
        factory = FactoryImpl.getInstance();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            doPost(request, response);
        } catch (Exception e) {
            LOGGER.error("Erreur lors de l'appel au doPost", e);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String servletPath = request.getServletPath();
        final String path = servletPath.substring(1, servletPath.lastIndexOf("frm") - 1);
        String view;
        final ActionXML action = factory.fabriqueAction(path);
        final IActionForm actionForm = factory.fabriqueActionForm(action.getFormName());
        final MyBeanPopulate localBeanPopulate = new MyBeanPopulate();
        localBeanPopulate.populateBean(actionForm, recupereParametresRequete(request));
        if (actionForm.validateForm().isEmpty()) {
            view = action.getAction().execute(request, response);
        } else {
            view = action.getFromView();
            request.setAttribute("erreurs", actionForm.validateForm());
        }
        if (view != null) {
            try {
                request.getRequestDispatcher(view).forward(request, response);
            } catch (Exception e) {
                LOGGER.error("Erreure dans la redirection", e);
            }
        }
    }

    /**
     * Pour convertir les éléments de la requete en une map de String String.
     * @param request la requete http.
     * @return une map avec le nom du paramètre et sa valeur.
     */
    private Map<String, String> recupereParametresRequete(HttpServletRequest request) {
        final Map<String, String[]> paramRequest = request.getParameterMap();
        final Map<String, String> aRenvoyer = new HashMap<>();
        for (String cle : paramRequest.keySet()) {
            aRenvoyer.put(cle, paramRequest.get(cle)[0]);
            request.setAttribute(cle, paramRequest.get(cle)[0]);
        }
        return aRenvoyer;
    }

}

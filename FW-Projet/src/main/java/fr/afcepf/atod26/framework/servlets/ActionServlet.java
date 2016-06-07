package fr.afcepf.atod26.framework.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.afcepf.atod26.framework.impl.FactoryImpl;

/**
 * Servlet implementation class ActionServlet
 */
@WebServlet("*.frm")
public class ActionServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(ActionServlet.class);

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        String path = servletPath.substring(1, servletPath.lastIndexOf("frm") - 1);
        String view = "index.html";
        view = FactoryImpl.fabriqueAction(path).execute(request, response);
        if (view != null) {
            try {
                request.getRequestDispatcher(view).forward(request, response);
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

}

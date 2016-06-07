package com.afcepf.atod26.framework.cours;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ServletDispatcher
 */
@WebServlet("*.frm")
public class ServletDispatcher extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Logger logger = Logger.getLogger(ServletDispatcher.class);

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        String path = servletPath.substring(1, servletPath.lastIndexOf("frm") - 1);
        String view = "index.html";
        if ("action1".equals(path)) {
            view = action1(request, response);
        } else if ("action2".equals(path)) {
            view = action2(request, response);
        } else {
            // Autre action
        }
        logger.info("La vue est " + view);
        if (view != null) {
            try {
                request.getRequestDispatcher(view).forward(request, response);
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    /**
     * Pour diriger vers la vue 1.
     * @param request
     * @param response
     * @return le chemine de la vue 1.
     */
    private String action1(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Je suis dans action 1");
        return "view1.jsp";
    }

    /**
     * Pour diriger vers la vue 2.
     * @param request
     * @param response
     * @return le chemin de la vue 2.
     */
    private String action2(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Je suis dans action 2");
        return "view2.jsp";
    }
}

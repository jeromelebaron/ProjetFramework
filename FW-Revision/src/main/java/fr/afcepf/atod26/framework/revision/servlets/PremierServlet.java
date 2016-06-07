package fr.afcepf.atod26.framework.revision.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afcepf.atod26.framework.revision.entity.Personne;
import fr.afcepf.atod26.framework.revision.service.PersonneService;

/**
 * Servlet implementation class PremierServlet
 */
@WebServlet("/premier")
public class PremierServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final PersonneService personneService = new PersonneService();

    /**
     * Default constructor.
     */
    public PremierServlet() {
        // EMPTY
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Personne> localPersonnes = personneService.getAll();
        request.setAttribute("personnes", localPersonnes);
        request.getRequestDispatcher("p1.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

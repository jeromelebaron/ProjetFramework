/*
 * Créé le 8 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Pour vérifier l'authentification et sécuriser les pages.
 * @author Jérome LE BARON
 * @author $LastChangedBy$
 * @version $Revision$ $Date$
 */
public class SecureFilter implements Filter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LoginBean loginBean = (LoginBean) ((HttpServletRequest) request).getSession().getAttribute(
                "loginBean");

        if (loginBean == null || !loginBean.isLogged()) {
            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + "/connexion.jsp");
        }

        chain.doFilter(request, response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(FilterConfig paramArg0) throws ServletException {
    }

}

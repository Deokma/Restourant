package by.popolamov.restourant.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * The Page redirect security filter. It prevents access to pages.
 */
@WebFilter(urlPatterns = {"/pages/*"},
        initParams = {@WebInitParam(name = "ERROR_404_PAGE", value = "/pages/error/error404.jsp")})
public class PageRedirectSecurityFilter implements Filter {
    private String error404Page;

    @Override
    public void init(FilterConfig config) {
        error404Page = config.getInitParameter("ERROR_404_PAGE");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (((HttpServletRequest) request).getSession(false) == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + error404Page);
        }
        chain.doFilter(request, response);
    }
}

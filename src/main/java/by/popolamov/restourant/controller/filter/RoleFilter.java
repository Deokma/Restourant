package by.popolamov.restourant.controller.filter;

import by.popolamov.restourant.controller.command.CommandType;
import by.popolamov.restourant.controller.command.RequestParameter;
import by.popolamov.restourant.controller.command.SessionAttribute;
import by.popolamov.restourant.model.entity.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.EnumSet;

/**
 * The Role filter. It prevents access to commands of other roles.
 */
@WebFilter(urlPatterns = { "/*" },
        initParams = { @WebInitParam(name = "INDEX_PATH", value = "/index.jsp"),
                @WebInitParam(name = "ERROR_404_PAGE", value = "/pages/error/error404.jsp")})
public class RoleFilter implements Filter {
    private EnumSet<CommandType> generalCommands;
    private EnumSet<CommandType> adminCommands;
    private String indexPath;
    private String error404Page;
    @Override
    public void init(FilterConfig config) {
        generalCommands = EnumSet.range(CommandType.SIGN_IN, CommandType.CHANGE_LOCALE);
        adminCommands = EnumSet.range(CommandType.GET_USERS, CommandType.ADD_MENU);
        indexPath = config.getInitParameter("INDEX_PATH");
        error404Page = config.getInitParameter("ERROR_404_PAGE");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String commandName = httpRequest.getParameter(RequestParameter.COMMAND);
        UserRole userRole = (UserRole) httpRequest.getSession().getAttribute(SessionAttribute.ROLE);
        if (commandName == null) {
            chain.doFilter(request, response);
            return;
        }
        if (userRole == null) {
            if (generalCommands.stream().anyMatch(commandType -> commandType.toString().toLowerCase().equals(commandName))) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + error404Page);
            }
            return;
        }
        if (userRole == UserRole.ADMIN) {
            chain.doFilter(request, response);
            return;
        }
        if (adminCommands.stream().anyMatch(commandType -> commandType.toString().toLowerCase().equals(commandName))) {
            ((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + error404Page);
            return;
        }
        chain.doFilter(request, response);
    }
}

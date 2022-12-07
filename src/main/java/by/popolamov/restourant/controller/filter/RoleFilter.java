package by.radzionau.imdb.controller.filter;

import by.radzionau.imdb.controller.command.CommandType;
import by.radzionau.imdb.controller.command.RequestParameter;
import by.radzionau.imdb.controller.command.SessionAttribute;
import by.radzionau.imdb.model.entity.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.EnumSet;

/**
 * The Role filter. It prevents access to commands of other roles.
 */
@WebFilter(urlPatterns = { "/*" })
public class RoleFilter implements Filter {
    private EnumSet<CommandType> generalCommands;
    private EnumSet<CommandType> adminCommands;

    @Override
    public void init(FilterConfig config) {
        generalCommands = EnumSet.range(CommandType.SIGN_IN, CommandType.CHANGE_LOCALE);
        adminCommands = EnumSet.range(CommandType.GET_USERS, CommandType.ADD_GENRE);
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
                ((HttpServletResponse) response).sendError(404);
            }
            return;
        }
        if (userRole == UserRole.ADMIN) {
            chain.doFilter(request, response);
            return;
        }
        if (adminCommands.stream().anyMatch(commandType -> commandType.toString().toLowerCase().equals(commandName))) {
            ((HttpServletResponse) response).sendError(404);
            return;
        }
        chain.doFilter(request, response);
    }
}

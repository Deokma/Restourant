package by.popolamov.restourant.controller.command.impl.admin;

import by.popolamov.restourant.controller.command.*;
import by.popolamov.restourant.controller.command.RequestUtil;
import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.entity.User;
import by.popolamov.restourant.model.entity.UserRole;
import by.popolamov.restourant.model.service.UserService;
import by.popolamov.restourant.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The class ChangeUserRoleCommand.
 */
public class ChangeUserRoleCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeUserRoleCommand.class);
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            String userLogin = requestUtil.getParameterAsString(request, RequestParameter.LOGIN);
            User userToChange = userService.findUserByLogin(userLogin);
            UserRole newUserRole = userToChange.getRole().equals(UserRole.USER) ? UserRole.ADMIN : UserRole.USER;
            userService.updateRole(userToChange, newUserRole);
            List<User> users = userService.findAll();
            removeCurrentUserFromList(request, users);
            request.setAttribute(RequestAttribute.USERS_LIST, users);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at ChangeUserRoleCommand", e);
            router = new Router(PagePath.INDEX_PAGE.getAddress(), Router.RouterType.FORWARD);
        }
        return router;
    }

    private void removeCurrentUserFromList(HttpServletRequest request, List<User> users) {
        User currentUser = (User) request.getSession().getAttribute(SessionAttribute.USER);
        users.remove(currentUser);
    }
}

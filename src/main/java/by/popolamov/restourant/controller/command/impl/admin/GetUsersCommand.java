package by.radzionau.imdb.controller.command.impl.admin;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.User;
import by.radzionau.imdb.model.service.UserService;
import by.radzionau.imdb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The class GetUsersCommand.
 */
public class GetUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GetUsersCommand.class);
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try {
            List<User> users = userService.findAll();
            removeCurrentUserFromList(request, users);
            request.setAttribute(RequestAttribute.USERS_LIST, users);
            router = new Router(PagePath.GET_USERS_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at GetUsersCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }
        return router;
    }

    private void removeCurrentUserFromList(HttpServletRequest request, List<User> users) {
        User currentUser = (User) request.getSession().getAttribute(SessionAttribute.USER);
        users.remove(currentUser);
    }
}

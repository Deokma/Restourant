package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.controller.command.RequestUtil;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.User;
import by.radzionau.imdb.model.entity.UserStatus;
import by.radzionau.imdb.model.service.UserService;
import by.radzionau.imdb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class SignInCommand.
 */
public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignInCommand.class);
    private static final UserService service = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            String login = requestUtil.getParameterAsString(request, RequestParameter.LOGIN);
            String password = requestUtil.getParameterAsString(request, RequestParameter.PASSWORD);
            User user = service.signIn(login, password);
            if (user.getStatus() == UserStatus.NON_ACTIVATED || user.getStatus() == UserStatus.BANNED) {
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, "your account non activated or banned");
                return new Router(PagePath.LOGIN_PAGE.getAddress(), Router.RouterType.FORWARD);
            }
            setSessionAttributes(request, user);
            router = new Router(PagePath.INDEX_PAGE.getAddress(), Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at SignInCommand", e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, "wrong login or password");
            router = new Router(PagePath.LOGIN_PAGE.getAddress(), Router.RouterType.FORWARD);
        }
        return router;
    }

    private void setSessionAttributes(HttpServletRequest request, User user) {
        request.getSession().setAttribute(SessionAttribute.USER, user);
        request.getSession().setAttribute(SessionAttribute.LOGIN, user.getLogin());
        request.getSession().setAttribute(SessionAttribute.ROLE, user.getRole());
    }
}

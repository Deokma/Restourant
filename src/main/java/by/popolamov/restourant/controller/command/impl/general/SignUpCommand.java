package by.popolamov.restourant.controller.command.impl.general;

import by.popolamov.restourant.controller.command.*;
import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.entity.User;
import by.popolamov.restourant.model.service.UserService;
import by.popolamov.restourant.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * The class SignUpCommand.
 */
public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignUpCommand.class);
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        Map<String, String> signupParameters = getSignupParameters(request);
        if (!isParametersValid(signupParameters)) {
            setSignupParameters(request, signupParameters);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, "wrong data");
            return new Router(PagePath.SIGNUP_PAGE.getAddress(), Router.RouterType.FORWARD);
        }
        try {
            User user = signUpUser(signupParameters);
            request.getSession().setAttribute(SessionAttribute.USER, user);
            router = new Router(PagePath.INDEX_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at SignUpCommand", e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, "wrong data");
            setSignupParameters(request, signupParameters);
            router = new Router(PagePath.SIGNUP_PAGE.getAddress(), Router.RouterType.FORWARD);
        }
        return router;
    }

    private User signUpUser(Map<String, String> signupParameters) throws ServiceException {
        return userService.signUp(
                signupParameters.get(RequestParameter.LOGIN),
                signupParameters.get(RequestParameter.PASSWORD),
                signupParameters.get(RequestParameter.REPEATED_PASSWORD),
                signupParameters.get(RequestParameter.FIRSTNAME),
                signupParameters.get(RequestParameter.FIRSTNAME)
        );
    }

    private Map<String, String> getSignupParameters(HttpServletRequest request) {
        Map<String, String> signUpParameters = new HashMap<>();
        signUpParameters.put(RequestParameter.LOGIN, request.getParameter(RequestParameter.LOGIN));
        signUpParameters.put(RequestParameter.PASSWORD, request.getParameter(RequestParameter.PASSWORD));
        signUpParameters.put(RequestParameter.REPEATED_PASSWORD, request.getParameter(RequestParameter.REPEATED_PASSWORD));
        signUpParameters.put(RequestParameter.FIRSTNAME, request.getParameter(RequestParameter.FIRSTNAME));
        signUpParameters.put(RequestParameter.LASTNAME, request.getParameter(RequestParameter.LASTNAME));
        return signUpParameters;
    }

    private boolean isParametersValid(Map<String, String> signupParameters) {
        boolean flag = true;
        for (Map.Entry<String, String> entry : signupParameters.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isEmpty()) {
                signupParameters.put(entry.getKey(), "");
                flag = false;
            }
        }

        if (isLoginPresence(signupParameters.get(RequestParameter.LOGIN))) {
            signupParameters.put(RequestParameter.LOGIN, "");
            flag = false;
        }
        if (!signupParameters.get(RequestParameter.PASSWORD).equals(signupParameters.get(RequestParameter.REPEATED_PASSWORD))) {
            signupParameters.put(RequestParameter.PASSWORD, "");
            signupParameters.put(RequestParameter.REPEATED_PASSWORD, "");
            flag = false;
        }

        return flag;
    }

    private boolean isLoginPresence(String login) {
        try {
            userService.findUserByLogin(login);
            return true;
        } catch (ServiceException e) {
            return false;
        }
    }

    private void setSignupParameters(HttpServletRequest request, Map<String, String> signupParameters) {
        signupParameters.forEach(request::setAttribute);
    }
}

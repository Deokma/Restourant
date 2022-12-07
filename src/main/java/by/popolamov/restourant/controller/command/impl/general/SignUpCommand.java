package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.User;
import by.radzionau.imdb.model.service.UserService;
import by.radzionau.imdb.model.service.impl.UserServiceImpl;
import by.radzionau.imdb.model.validator.UserValidator;
import by.radzionau.imdb.util.mail.EmailSenderUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import java.io.IOException;
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
            EmailSenderUtil.getInstance().sendAuthenticationMessage(user);
            request.getSession().setAttribute(SessionAttribute.USER, user);
            router = new Router(PagePath.VERIFY_EMAIL_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException | MessagingException | IOException e) {
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
                signupParameters.get(RequestParameter.EMAIL),
                signupParameters.get(RequestParameter.FIRST_NAME),
                signupParameters.get(RequestParameter.SURNAME)
        );
    }

    private Map<String, String> getSignupParameters(HttpServletRequest request) {
        Map<String, String> signUpParameters = new HashMap<>();
        signUpParameters.put(RequestParameter.LOGIN, request.getParameter(RequestParameter.LOGIN));
        signUpParameters.put(RequestParameter.PASSWORD, request.getParameter(RequestParameter.PASSWORD));
        signUpParameters.put(RequestParameter.REPEATED_PASSWORD, request.getParameter(RequestParameter.REPEATED_PASSWORD));
        signUpParameters.put(RequestParameter.EMAIL, request.getParameter(RequestParameter.EMAIL));
        signUpParameters.put(RequestParameter.FIRST_NAME, request.getParameter(RequestParameter.FIRST_NAME));
        signUpParameters.put(RequestParameter.SURNAME, request.getParameter(RequestParameter.SURNAME));
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
        if (!UserValidator.getInstance().isEmailValid(signupParameters.get(RequestParameter.EMAIL))) {
            signupParameters.put(RequestParameter.EMAIL, "");
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

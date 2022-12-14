package by.popolamov.restourant.controller.command;

import by.popolamov.restourant.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.Scanner;

/**
 * The class RequestUtil.
 */
public final class RequestUtil {
    private static final Logger logger = LogManager.getLogger(RequestUtil.class);
    private static final RequestUtil INSTANCE = new RequestUtil();

    private RequestUtil() {}

    /**
     * Gets instance.
     *
     * @return the instance of request util.
     */
    public static RequestUtil getInstance() {
        return INSTANCE;
    }

    /**
     * Gets parameter as int. Throws ServiceException if parameter is invalid.
     *
     * @param request       the request
     * @param parameterName the parameter name
     * @return the parameter as int
     * @throws ServiceException if parameter is invalid
     */
    public int getParameterAsInt(HttpServletRequest request, String parameterName) throws ServiceException {
        Optional<String> parameter = getParameter(request, parameterName);
        if (parameter.isPresent()) {
            try(Scanner scanner = new Scanner(parameter.get())) {
                if (scanner.hasNextInt()) {
                    return Integer.parseInt(parameter.get());
                } else {
                    logger.error("Invalid parameter");
                    throw new ServiceException("Invalid parameter");
                }
            }
        } else {
            logger.error("Invalid parameter");
            throw new ServiceException("Invalid parameter");
        }
    }

    /**
     * Gets parameter as string. Throws ServiceException is parameter is null or empty.
     *
     * @param request       the request
     * @param parameterName the parameter name
     * @return the parameter as string
     * @throws ServiceException if parameter is null or empty
     */
    public String getParameterAsString(HttpServletRequest request, String parameterName) throws ServiceException {
        Optional<String> parameter = getParameter(request, parameterName);
        if (!parameter.isPresent()) {
            logger.error("Invalid parameter");
            throw new ServiceException("Invalid parameter");
        }
        return parameter.get();
    }

    private Optional<String> getParameter(HttpServletRequest request, String parameterName) {
        String parameter = request.getParameter(parameterName);
        if (parameter == null) {
            return Optional.empty();
        } else {
            return Optional.of(parameter);
        }
    }
}

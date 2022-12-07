package by.radzionau.imdb.controller.command;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.FeedbackStatus;
import by.radzionau.imdb.model.entity.MovieType;
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
     * Gets parameter as long. Throws ServiceException if parameter is invalid.
     *
     * @param request       the request
     * @param parameterName the parameter name
     * @return the parameter as long
     * @throws ServiceException if parameter is invalid
     */
    public Long getParameterAsLong(HttpServletRequest request, String parameterName) throws ServiceException {
        Optional<String> parameter = getParameter(request, parameterName);
        if (parameter.isPresent()) {
            try (Scanner scanner = new Scanner(parameter.get())) {
                if (scanner.hasNextLong()) {
                    return Long.valueOf(parameter.get());
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
     * Gets parameter as movie type. Throws ServiceException if parameter is invalid.
     *
     * @param request the request
     * @return the parameter as movie type
     * @throws ServiceException if parameter is invalid
     */
    public MovieType getParameterAsMovieType(HttpServletRequest request) throws ServiceException {
        Optional<String> parameter = getParameter(request, RequestParameter.MOVIE_TYPE);
        if (parameter.isPresent()) {
            try {
                return MovieType.valueOf(parameter.get().toUpperCase());
            } catch (IllegalArgumentException e) {
                logger.error("Invalid parameter");
                throw new ServiceException("Invalid parameter");
            }
        } else {
            logger.error("Invalid parameter");
            throw new ServiceException("Invalid parameter");
        }
    }

    /**
     * Gets parameter as feedback status. Throws ServiceException if parameter is invalid.
     *
     * @param request the request
     * @return the parameter as feedback status
     * @throws ServiceException if parameter is invalid
     */
    public FeedbackStatus getParameterAsFeedbackStatus(HttpServletRequest request) throws ServiceException {
        Optional<String> parameter = getParameter(request, RequestParameter.FEEDBACK_STATUS);
        if (parameter.isPresent()) {
            try {
                return FeedbackStatus.valueOf(parameter.get().toUpperCase());
            } catch (IllegalArgumentException e) {
                logger.error("Invalid parameter");
                throw new ServiceException("Invalid parameter");
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

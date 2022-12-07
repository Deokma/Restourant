package by.popolamov.restourant.controller.command;

import jakarta.servlet.http.HttpServletRequest;
/**
 * The interface Command.
 */
public interface Command {
    /**
     * Execute specified command.
     *
     * @param request the request
     * @return the router with specified next page and type of routing
     */
    Router execute(HttpServletRequest request);

    /**
     * Gets URL of previous page in string.
     *
     * @param request the request
     * @return URL of previous page in string
     */
    default String getPageFrom(HttpServletRequest request) {
        String pageTo = (String) request.getAttribute(RequestAttribute.PAGE_FROM);
        if (pageTo == null || pageTo.isEmpty()) {
            pageTo = PagePath.INDEX_PAGE.getAddress();
        }
        return pageTo;
    }
}


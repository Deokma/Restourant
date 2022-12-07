package by.popolamov.restourant.controller.command.impl.general;

import by.popolamov.restourant.controller.command.Command;
import by.popolamov.restourant.controller.command.PagePath;
import by.popolamov.restourant.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The class DefaultCommand.
 */
public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.INDEX_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}

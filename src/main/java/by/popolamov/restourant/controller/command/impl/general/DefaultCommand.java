package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.PagePath;
import by.radzionau.imdb.controller.command.Router;
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

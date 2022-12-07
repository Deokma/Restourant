package by.radzionau.imdb.controller.command.impl.moveto.general;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.PagePath;
import by.radzionau.imdb.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class MoveToSignupPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.SIGNUP_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}

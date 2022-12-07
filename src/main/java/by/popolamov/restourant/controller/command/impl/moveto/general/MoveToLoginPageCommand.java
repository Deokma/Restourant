package by.popolamov.restourant.controller.command.impl.moveto.general;

import by.popolamov.restourant.controller.command.Command;
import by.popolamov.restourant.controller.command.PagePath;
import by.popolamov.restourant.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class MoveToLoginPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.LOGIN_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}

package by.popolamov.restourant.controller.command.impl.moveto.admin;

import by.popolamov.restourant.controller.command.Command;
import by.popolamov.restourant.controller.command.PagePath;
import by.popolamov.restourant.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class OrderRedirectPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        router = new Router(PagePath.ORDERS_PAGE.getAddress(), Router.RouterType.REDIRECT);
        return router;
    }
}

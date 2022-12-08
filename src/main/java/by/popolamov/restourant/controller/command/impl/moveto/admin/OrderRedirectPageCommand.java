package by.popolamov.restourant.controller.command.impl.moveto.admin;

import by.popolamov.restourant.controller.command.Command;
import by.popolamov.restourant.controller.command.PagePath;
import by.popolamov.restourant.controller.command.Router;
import by.popolamov.restourant.controller.command.*;
import by.popolamov.restourant.controller.command.RequestUtil;
import by.popolamov.restourant.controller.command.impl.moveto.user.MoveToCartPageCommand;
import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.entity.*;
import by.popolamov.restourant.model.service.MenuOrderService;
import by.popolamov.restourant.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.popolamov.restourant.model.service.OrderService;
import by.popolamov.restourant.model.service.impl.MenuOrderServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class OrderRedirectPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MoveToCartPageCommand.class);
    private static final MenuOrderService menuOrderService = MenuOrderServiceImpl.getInstance();
    private static final OrderService orderService = OrderServiceImpl.getInstance();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        router = new Router(PagePath.ORDERS_PAGE.getAddress(), Router.RouterType.REDIRECT);
        return router;
    }
}

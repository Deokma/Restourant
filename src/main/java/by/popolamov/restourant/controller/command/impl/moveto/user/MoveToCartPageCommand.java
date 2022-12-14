package by.popolamov.restourant.controller.command.impl.moveto.user;

import by.popolamov.restourant.controller.command.*;
import by.popolamov.restourant.controller.command.RequestUtil;
import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.entity.*;
import by.popolamov.restourant.model.service.MenuOrderService;
import by.popolamov.restourant.model.service.OrderService;
import by.popolamov.restourant.model.service.impl.MenuOrderServiceImpl;
import by.popolamov.restourant.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The class MoveToCartPageCommand.
 */
public class MoveToCartPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MoveToCartPageCommand.class);
    private static final MenuOrderService menuOrderService = MenuOrderServiceImpl.getInstance();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        try {
            List<MenuOrder> menuOrderList = new ArrayList<>();
            menuOrderList.addAll(menuOrderService.findCartByQuentity(MenuOrderQuantity.ONE));
            request.setAttribute(RequestAttribute.MENU_ORDER, menuOrderList);
            router = new Router(PagePath.CART_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at MoveToCartPageCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }
        return router;
    }
}

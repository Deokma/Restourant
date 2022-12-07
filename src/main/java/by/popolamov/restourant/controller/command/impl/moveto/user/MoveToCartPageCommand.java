package by.popolamov.restourant.controller.command.impl.moveto.user;

import by.popolamov.restourant.controller.command.*;
import by.popolamov.restourant.controller.command.RequestUtil;
import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.entity.*;
import by.popolamov.restourant.model.service.MenuOrderService;
import by.popolamov.restourant.model.service.MenuService;
import by.popolamov.restourant.model.service.OrderService;
import by.popolamov.restourant.model.service.impl.MenuOrderServiceImpl;
import by.popolamov.restourant.model.service.impl.MenuServiceImpl;
import by.popolamov.restourant.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The class MoveToCartPageCommand.
 */
public class MoveToCartPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MoveToCartPageCommand.class);
    private static final MenuOrderService menuOrderService = MenuOrderServiceImpl.getInstance();
    private static final OrderService orderService = OrderServiceImpl.getInstance();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            int userId = Integer.parseInt(request.getParameter("userid"));
            MenuOrder menuOrder = menuOrderService.findOrderByUserId(userId);
            request.setAttribute("menuorder", menuOrder);
            List<MenuOrder> menuOrderList = new ArrayList<>();
            menuOrderList.addAll(menuOrderService.findOrderByOrder(menuOrder));
            router = new Router(PagePath.CART_PAGE.getAddress(), Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at MoveToCartPageCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }
        return router;
        //return new Router(PagePath.CART_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}

package by.popolamov.restourant.controller.command.impl.moveto.admin;

import by.popolamov.restourant.controller.command.*;
import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.entity.Menu;
import by.popolamov.restourant.model.entity.MenuCategory;
import by.popolamov.restourant.model.entity.Order;
import by.popolamov.restourant.model.entity.OrderStatus;
import by.popolamov.restourant.model.service.MenuService;
import by.popolamov.restourant.model.service.OrderService;
import by.popolamov.restourant.model.service.impl.MenuServiceImpl;
import by.popolamov.restourant.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The class MoveToCartPageCommand.
 */
public class MoveToOrdersPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MoveToOrdersPageCommand.class);
    private static final OrderService orderService = OrderServiceImpl.getInstance();
    @Override
    public Router execute(HttpServletRequest request) {
        try {
            List<Order> ordersList = new ArrayList<>();
            ordersList.addAll(orderService.findOrderByOrderStatus(OrderStatus.INPROGRESS));
            ordersList.addAll(orderService.findOrderByOrderStatus(OrderStatus.COMPLETE));
            List<Order> menuOrdersList = new ArrayList<>();
//            for (Order order : menuOrdersList) {
//                //menuOrdersList.addAll(orderService.findOrderByOrder(order));
//            }
            request.setAttribute(RequestAttribute.ORDER_LIST, ordersList);
            request.setAttribute(RequestAttribute.MENU_ORDER_LIST, menuOrdersList);
        } catch (ServiceException e) {
            logger.error("Error at MoveToMainPageCommand", e);
        }
        return new Router(PagePath.ORDERS_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}

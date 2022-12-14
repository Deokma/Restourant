package by.popolamov.restourant.controller.command.impl.moveto.admin;

import by.popolamov.restourant.controller.command.*;
import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.entity.Order;
import by.popolamov.restourant.model.entity.OrderStatus;
import by.popolamov.restourant.model.service.OrderService;
import by.popolamov.restourant.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The class MoveToOrdersPageCommand.
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
            request.setAttribute(RequestAttribute.ORDER_LIST, ordersList);
        } catch (ServiceException e) {
            logger.error("Error at MoveToOrdersPageCommand", e);
        }
        return new Router(PagePath.ORDERS_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}

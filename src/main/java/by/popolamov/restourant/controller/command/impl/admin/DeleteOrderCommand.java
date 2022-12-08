package by.popolamov.restourant.controller.command.impl.admin;

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
 * The class CompleteOrderCommand.
 */
public class DeleteOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteOrderCommand.class);
    private static final OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            int orderid = requestUtil.getParameterAsInt(request, RequestParameter.ORDER_ID);
            Order order = orderService.findOrderByIdOrderClass(orderid);
            orderService.delete(order);
            List<Order> ordersList = new ArrayList<>();
            ordersList.addAll(orderService.findOrderByOrderStatus(OrderStatus.INPROGRESS));
            ordersList.addAll(orderService.findOrderByOrderStatus(OrderStatus.COMPLETE));
            request.setAttribute(RequestAttribute.ORDER_LIST, ordersList);
            router = new Router(PagePath.ORDERS_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at CompleteOrderCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(PagePath.INDEX_PAGE.getAddress(),Router.RouterType.REDIRECT);
        }
        return router;
    }
}

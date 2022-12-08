package by.popolamov.restourant.controller.command.impl.moveto.user;

import by.popolamov.restourant.controller.command.Command;
import by.popolamov.restourant.controller.command.PagePath;
import by.popolamov.restourant.controller.command.RequestAttribute;
import by.popolamov.restourant.controller.command.Router;
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
 * The class MoveToCartPageCommand.
 */
public class MoveToAccountPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MoveToAccountPageCommand.class);
    private static final OrderService orderService = OrderServiceImpl.getInstance();
    @Override
    public Router execute(HttpServletRequest request) {
        try {
            List<Order> ordersList = new ArrayList<>();
            ordersList.addAll(orderService.findOrderByOrderStatus(OrderStatus.INPROGRESS));
            ordersList.addAll(orderService.findOrderByOrderStatus(OrderStatus.COMPLETE));
            request.setAttribute(RequestAttribute.ORDER_LIST, ordersList);
        } catch (ServiceException e) {
            logger.error("Error at MoveToAccountPageCommand", e);
        }
        return new Router(PagePath.ACCOUNT_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}

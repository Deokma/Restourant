package by.popolamov.restourant.model.service.impl;

import by.popolamov.restourant.exception.DaoException;
import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.dao.OrderDao;
import by.popolamov.restourant.model.dao.impl.OrderDaoImpl;
import by.popolamov.restourant.model.entity.Menu;
import by.popolamov.restourant.model.entity.Order;
import by.popolamov.restourant.model.entity.OrderStatus;
import by.popolamov.restourant.model.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger(MenuServiceImpl.class);
    private final OrderDao orderDao = OrderDaoImpl.getInstance();

    private static final class OrderServiceInstanceHolder {
        private static final OrderServiceImpl INSTANCE = new OrderServiceImpl();
    }
    private OrderServiceImpl() {

    }
    /**
     * Gets instance of movie service.
     *
     * @return the instance of movie service
     */
    public static OrderService getInstance() {
        return OrderServiceImpl.OrderServiceInstanceHolder.INSTANCE;
    }
    @Override
    public void update(Order order) throws ServiceException {
        try {
            orderDao.update(order);
        } catch (DaoException e) {
            logger.error("Can't handle update request at MenuService", e);
            throw new ServiceException("Can't handle update request at MenuService", e);
        }
    }

    @Override
    public void delete(Order order) throws ServiceException {
        try {
            orderDao.delete(order);
        } catch (DaoException e) {
            logger.error("Can't handle deleteMenu request at MenuService", e);
            throw new ServiceException("Can't handle deleteMenu request at MenuService", e);
        }
    }

    @Override
    public List<Order> findOrderByOrderId(Order order) throws ServiceException {
        return findOrderByIdOrder(order.getOrderid());
    }
    @Override
    public Order findOrderByIdOrderClass(int orderId) throws ServiceException {
        if (orderId == 0) {
            logger.error("MovieId doesn't present");
            throw new ServiceException("MovieId doesn't present");
        }
        try {
            Optional<Order> optionalOrder = orderDao.findOrderByOrderId(orderId);
            if (optionalOrder.isPresent()) {
                return optionalOrder.get();
            } else {
                logger.error("Movie with id={} not found", orderId);
                throw new ServiceException("Movie with id=" + orderId + " not found");
            }
        } catch (DaoException e) {
            logger.error("Can't handle getMovieById request at MovieService", e);
            throw new ServiceException("Can't handle getMovieById request at MovieService", e);
        }
    }
    @Override
    public List<Order> findOrderByIdOrder(int orderid) throws ServiceException {
        if (orderid == 0) {
            logger.error("Title doesn't present");
            throw new ServiceException("Title doesn't present");
        }
        List<Order> orders;
        try {
            orders = orderDao.findOrderByIdOrder(orderid);
        } catch (DaoException e) {
            logger.error("Can't handle findMenuByDishName request at MenuService", e);
            throw new ServiceException("Can't handle findMenuByDishName request at MenuService", e);
        }
        return orders;
    }

    @Override
    public List<Order> findOrderByOrderStatus(OrderStatus orderStatus) throws ServiceException {
        if (orderStatus == null) {
            logger.error("orderStatus doesn't present");
            throw new ServiceException("orderStatus doesn't present");
        }

        List<Order> orders;
        try {
            orders = orderDao.findOrderByOrderStatus(orderStatus);
        } catch (DaoException e) {
            logger.error("Can't handle findOrderByOrderStatus request at MenuService", e);
            throw new ServiceException("Can't handle findOrderByOrderStatus request at MenuService", e);
        }
        return orders;
    }
}

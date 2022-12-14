package by.popolamov.restourant.model.service;

import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.entity.Order;
import by.popolamov.restourant.model.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    /**
     * Update order. Throws ServiceException if order is null or if writing to data source throws the exception.
     *
     * @param order the updated order
     * @throws ServiceException if order is null or if writing to data source throws the exception
     */
    void update(Order order) throws ServiceException;

    /**
     * Delete order. Throws ServiceException if order is null or if updating of data source throws the exception.
     *
     * @param order the order to delete
     * @throws ServiceException if order is null or if updating of data source throws the exception
     */
    void delete(Order order) throws ServiceException;

    /**
     * Find movie by order id. Throws ServiceException if order id is null or if reading from data source throws the exception.
     *
     * @param orderid the order id
     * @return the order
     * @throws ServiceException if order id is null or if reading from data source throws the exception
     */
    List<Order> findOrderByIdOrder(int orderid) throws ServiceException;
    /**
     * Find movie by order id. Throws ServiceException if order id is null or if reading from data source throws the exception.
     *
     * @param orderid the order id
     * @return the order
     * @throws ServiceException if order id is null or if reading from data source throws the exception
     */
    Order findOrderByIdOrderClass(int orderid) throws ServiceException;
    /**
     * Find movie by order id. Throws ServiceException if order id is null or if reading from data source throws the exception.
     *
     * @param order the order id
     * @return the order id
     * @throws ServiceException if order id is null or if reading from data source throws the exception
     */
    List<Order> findOrderByOrderId(Order order) throws ServiceException;
    /**
     * Find movie by movie id. Throws ServiceException if order id is null or if reading from data source throws the exception.
     *
     * @param orderStatus the order id
     * @return the order
     * @throws ServiceException if order id is null or if reading from data source throws the exception
     */
    List<Order> findOrderByOrderStatus(OrderStatus orderStatus) throws ServiceException;
}

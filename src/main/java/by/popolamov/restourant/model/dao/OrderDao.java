package by.popolamov.restourant.model.dao;

import by.popolamov.restourant.exception.DaoException;
import by.popolamov.restourant.model.entity.Order;
import by.popolamov.restourant.model.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    /**
     * Update order. Throws DaoException if updating of data source throws exception.
     *
     * @param order the updated order
     * @return the count of updated rows
     * @throws DaoException if updating of data source throws exception
     */
    int update(Order order) throws DaoException;

    /**
     * Delete order. Throws DaoException if updating of data source throws exception.
     *
     * @param order the order to delete
     * @return
     * @throws DaoException if updating of data source throws exception
     */
    int delete(Order order) throws DaoException;

    /**
     * Find order by orderid. Throws DaoException if reading of data source throws exception.
     *
     * @param orderid the orderid
     * @return the optional of order
     * @throws DaoException if reading of data source throws exception
     */
    Optional<Order> findOrderByOrderId(int orderid) throws DaoException;
    /**
     * Find order by orderid. Throws DaoException if reading of data source throws exception.
     *
     * @param order the order id
     * @return the optional of order
     * @throws DaoException if reading of data source throws exception
     */
    List<Order> findOrderByIdOrder(int order) throws DaoException;
    /**
     * Find order by orderstatus. Throws DaoException if reading of data source throws exception.
     *
     * @param orderStatus the orderstatus
     * @return the optional of order
     * @throws DaoException if reading of data source throws exception
     */
    List<Order> findOrderByOrderStatus(OrderStatus orderStatus) throws DaoException;
}

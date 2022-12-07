package by.popolamov.restourant.model.dao;

import by.popolamov.restourant.exception.DaoException;
import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.entity.Order;
import by.popolamov.restourant.model.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    /**
     * Update movie. Throws DaoException if updating of data source throws exception.
     *
     * @param order the updated movie
     * @return the count of updated rows
     * @throws DaoException if updating of data source throws exception
     */
    int update(Order order) throws DaoException;

    /**
     * Delete movie. Throws DaoException if updating of data source throws exception.
     *
     * @param order the movie to delete
     * @return
     * @throws DaoException if updating of data source throws exception
     */
    int delete(Order order) throws DaoException;

    /**
     * Find movie by id. Throws DaoException if reading of data source throws exception.
     *
     * @param order the movie id
     * @return the optional of movie
     * @throws DaoException if reading of data source throws exception
     */
    Optional<Order> findOrderByOrderId(int order) throws DaoException;
    /**
     * Find movie by id. Throws DaoException if reading of data source throws exception.
     *
     * @param order the movie id
     * @return the optional of movie
     * @throws DaoException if reading of data source throws exception
     */
    List<Order> findOrderByIdOrder(int order) throws DaoException;
    /**
     * Find movie by movie id. Throws ServiceException if movie id is null or if reading from data source throws the exception.
     *
     * @param orderid the movie id
     * @return the movie
     * @throws ServiceException if movie id is null or if reading from data source throws the exception
     */
    Optional<Order> findOrderByIdOrderClass(int orderid) throws ServiceException, DaoException;
    /**
     * Find movie by id. Throws DaoException if reading of data source throws exception.
     *
     * @param orderStatus the movie id
     * @return the optional of movie
     * @throws DaoException if reading of data source throws exception
     */
    List<Order> findOrderByOrderStatus(OrderStatus orderStatus) throws DaoException;
}

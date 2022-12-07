package by.popolamov.restourant.model.dao;

import by.popolamov.restourant.exception.DaoException;
import by.popolamov.restourant.model.entity.Menu;
import by.popolamov.restourant.model.entity.MenuOrder;

import java.util.List;
import java.util.Optional;

public interface MenuOrderDao {
    /**
     * Add menu. Throws DaoException if writing to data source throws exception.
     *
     * @param menuOrder the movie to add
     * @return the count of updated rows
     * @throws DaoException if writing to data source throws exception
     */
    int add(MenuOrder menuOrder) throws DaoException;
    /**
     * Delete menu. Throws DaoException if updating of data source throws exception.
     *
     * @param menuOrder the movie to delete
     * @return
     * @throws DaoException if updating of data source throws exception
     */
    int delete(MenuOrder menuOrder) throws DaoException;

    /**
     * Update menu. Throws DaoException if updating of data source throws exception.
     *
     * @param menuOrder the movie to delete
     * @return
     * @throws DaoException if updating of data source throws exception
     */
    int update(MenuOrder menuOrder) throws DaoException;
    Optional<MenuOrder> findMenuByDishId(int dishId) throws DaoException;
    /**
     * Find movie by id. Throws DaoException if reading of data source throws exception.
     *
     * @param userid the movie id
     * @return the optional of movie
     * @throws DaoException if reading of data source throws exception
     */
    Optional<MenuOrder> findMenuOrderByUserId(int userid) throws DaoException;

    List<MenuOrder> findOrderByOrder(MenuOrder order) throws DaoException;
}

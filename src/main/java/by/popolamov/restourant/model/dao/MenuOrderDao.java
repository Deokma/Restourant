package by.popolamov.restourant.model.dao;

import by.popolamov.restourant.exception.DaoException;
import by.popolamov.restourant.model.entity.MenuOrder;
import by.popolamov.restourant.model.entity.MenuOrderQuantity;

import java.util.List;
import java.util.Optional;

public interface MenuOrderDao {
    /**
     * Add menuorder. Throws DaoException if writing to data source throws exception.
     *
     * @param menuOrder the menuorder to add
     * @return the count of updated rows
     * @throws DaoException if writing to data source throws exception
     */
    int add(MenuOrder menuOrder) throws DaoException;
    /**
     * Delete menuorder. Throws DaoException if updating of data source throws exception.
     *
     * @param menuOrder the menuorder to delete
     * @return
     * @throws DaoException if updating of data source throws exception
     */
    int delete(MenuOrder menuOrder) throws DaoException;

    /**
     * Update menuorder. Throws DaoException if updating of data source throws exception.
     *
     * @param menuOrder the menuorder to delete
     * @return
     * @throws DaoException if updating of data source throws exception
     */
    int update(MenuOrder menuOrder) throws DaoException;
    /**
     * Find menuorder by Dish id. Throws DaoException if reading of data source throws exception.
     *
     * @param dishId the dish id
     * @return the optional of menuorder
     * @throws DaoException if reading of data source throws exception
     */
    Optional<MenuOrder> findMenuByDishId(int dishId) throws DaoException;
    /**
     * Find menuorder by User id. Throws DaoException if reading of data source throws exception.
     *
     * @param userid the user id
     * @return the optional of menuorder
     * @throws DaoException if reading of data source throws exception
     */
    Optional<MenuOrder> findMenuOrderByUserId(int userid) throws DaoException;
    /**
     * Find menuorder by Order. Throws DaoException if reading of data source throws exception.
     *
     * @param order the order
     * @return the optional of menuorder
     * @throws DaoException if reading of data source throws exception
     */

    List<MenuOrder> findOrderByOrder(MenuOrder order) throws DaoException;
    /**
     * Find menuorder by menuOrderQuantity. Throws DaoException if reading of data source throws exception.
     *
     * @param menuOrderQuantity the menuorder quantity
     * @return the optional of menuorder
     * @throws DaoException if reading of data source throws exception
     */

    List<MenuOrder> findCartByQuentity(MenuOrderQuantity menuOrderQuantity) throws DaoException;
}

package by.popolamov.restourant.model.dao;

import by.popolamov.restourant.exception.DaoException;
import by.popolamov.restourant.model.entity.Menu;
import by.popolamov.restourant.model.entity.MenuCategory;

import java.util.List;
import java.util.Optional;

/**
 * Interface describes the behavior of {@link Menu} entity
 *
 * @author Denis Popolamov
 */
public interface MenuDao {
    /**
     * Add menu. Throws DaoException if writing to data source throws exception.
     *
     * @param menu the menu to add
     * @return the count of updated rows
     * @throws DaoException if writing to data source throws exception
     */
    int add(Menu menu) throws DaoException;

    /**
     * Update menu. Throws DaoException if updating of data source throws exception.
     *
     * @param menu the updated menu
     * @return the count of updated rows
     * @throws DaoException if updating of data source throws exception
     */
    int update(Menu menu) throws DaoException;

    /**
     * Delete menu. Throws DaoException if updating of data source throws exception.
     *
     * @param menu the menu to delete
     * @return
     * @throws DaoException if updating of data source throws exception
     */
    int delete(Menu menu) throws DaoException;

    /**
     * Find menu by category id. Throws DaoException if reading of data source throws exception.
     *
     * @param categoryid the menu id
     * @return the optional of menu
     * @throws DaoException if reading of data source throws exception
     */
    List<Menu> findMenuByCategoryId(MenuCategory categoryid) throws DaoException;

    /**
     * Find menu by dish id. Throws DaoException if reading of data source throws exception.
     *
     * @param dishId the menu id
     * @return the optional of menu
     * @throws DaoException if reading of data source throws exception
     */
    Optional<Menu> findMenuByDishId(int dishId) throws DaoException;

    /**
     * Find menu by dish name. Throws DaoException if reading of data source throws exception.
     *
     * @param dishname the title
     * @return the list of menu
     * @throws DaoException if reading of data source throws exception
     */
    List<Menu> findMenuByDishName(String dishname) throws DaoException;

}
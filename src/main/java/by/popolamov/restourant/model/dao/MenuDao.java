package by.popolamov.restourant.model.dao;

import by.popolamov.restourant.exception.DaoException;
import by.popolamov.restourant.model.entity.Menu;
import by.popolamov.restourant.model.entity.MenuCategory;

import java.util.List;
import java.util.Optional;

/**
 * Interface describes the behavior of {@link Menu} entity
 *
 * @author Anton Radzionau
 */
public interface MenuDao {
    /**
     * Add movie. Throws DaoException if writing to data source throws exception.
     *
     * @param menu the movie to add
     * @return the count of updated rows
     * @throws DaoException if writing to data source throws exception
     */
    int add(Menu menu) throws DaoException;

    /**
     * Update movie. Throws DaoException if updating of data source throws exception.
     *
     * @param menu the updated movie
     * @return the count of updated rows
     * @throws DaoException if updating of data source throws exception
     */
    int update(Menu menu) throws DaoException;

    /**
     * Delete movie. Throws DaoException if updating of data source throws exception.
     *
     * @param menu the movie to delete
     * @return
     * @throws DaoException if updating of data source throws exception
     */
    int delete(Menu menu) throws DaoException;

    /**
     * Find movie by id. Throws DaoException if reading of data source throws exception.
     *
     * @param categoryid the movie id
     * @return the optional of movie
     * @throws DaoException if reading of data source throws exception
     */
    List<Menu> findMenuByCategoryId(MenuCategory categoryid) throws DaoException;

    /**
     * Find movie by id. Throws DaoException if reading of data source throws exception.
     *
     * @param dishId the movie id
     * @return the optional of movie
     * @throws DaoException if reading of data source throws exception
     */
    Optional<Menu> findMenuByDishId(int dishId) throws DaoException;

    /**
     * Find movies by title. Throws DaoException if reading of data source throws exception.
     *
     * @param dishname the title
     * @return the list of movies
     * @throws DaoException if reading of data source throws exception
     */
    List<Menu> findMenuByDishName(String dishname) throws DaoException;

}
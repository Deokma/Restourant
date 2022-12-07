package by.popolamov.restourant.model.service;

import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.entity.Menu;
import by.popolamov.restourant.model.entity.MenuCategory;

import java.util.List;

/**
 * The interface MovieService provides methods to manage movies.
 */
public interface MenuService {
    /**
     * Add movie. Throws ServiceException if movie is null or if writing to data source throws the exception.
     *
     * @param menu the menu to add
     * @throws ServiceException if menu is null or if writing to data source throws the exception
     */
    void addMenu(Menu menu) throws ServiceException;

    /**
     * Update movie. Throws ServiceException if movie is null or if writing to data source throws the exception.
     *
     * @param menu the updated movie
     * @throws ServiceException if movie is null or if writing to data source throws the exception
     */
    void update(Menu menu) throws ServiceException;

    /**
     * Delete movie. Throws ServiceException if movie is null or if updating of data source throws the exception.
     *
     * @param menu the movie to delete
     * @throws ServiceException if movie is null or if updating of data source throws the exception
     */
    void deleteMenu(Menu menu) throws ServiceException;

    /**
     * Find movie by movie id. Throws ServiceException if movie id is null or if reading from data source throws the exception.
     *
     * @param dishid the movie id
     * @return the movie
     * @throws ServiceException if movie id is null or if reading from data source throws the exception
     */
    Menu findMenuByDishId(int dishid) throws ServiceException;

    /**
     * Find movies by title (search by part of title). Throws ServiceException if title is null or empty or if reading from data source throws the exception.
     *
     * @param dishname the title
     * @return the list of movies
     * @throws ServiceException if title is null or empty or if reading from data source throws the exception
     */
    List<Menu> findMenuByDishName(String dishname) throws ServiceException;

    /**
     * Find menu by categoryid type. Throws ServiceException if movie type is null or if reading from data source throws the exception.
     *
     * @param categoryId the movie type
     * @return the list of movies
     * @throws ServiceException if movie type is null or if reading from data source throws the exception
     */
    List<Menu> findMenuByCategoryId(MenuCategory categoryId) throws ServiceException;
    /**
     * Find movie score of movie. Throws ServiceException if movie is null or if movie does not exist or if reading from data source throws the exception.
     *
     * @param dishname the menu
     * @return the score of menu
     * @throws ServiceException if movie is null or if movie does not exist or if reading from data source throws the exception
     */
    List<Menu> findDish(Menu dishname) throws ServiceException;
}

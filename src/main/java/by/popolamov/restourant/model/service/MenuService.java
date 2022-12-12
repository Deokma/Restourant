package by.popolamov.restourant.model.service;

import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.entity.Menu;
import by.popolamov.restourant.model.entity.MenuCategory;

import java.util.List;

/**
 * The interface MenuService provides methods to manage movies.
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
     * Update movie. Throws ServiceException if menu is null or if writing to data source throws the exception.
     *
     * @param menu the updated menu
     * @throws ServiceException if menu is null or if writing to data source throws the exception
     */
    void update(Menu menu) throws ServiceException;

    /**
     * Delete movie. Throws ServiceException if menu is null or if updating of data source throws the exception.
     *
     * @param menu the menu to delete
     * @throws ServiceException if menu is null or if updating of data source throws the exception
     */
    void deleteMenu(Menu menu) throws ServiceException;

    /**
     * Find movie by menu id. Throws ServiceException if movie id is null or if reading from data source throws the exception.
     *
     * @param dishid the menu id
     * @return the menu
     * @throws ServiceException if menu id is null or if reading from data source throws the exception
     */
    Menu findMenuByDishId(int dishid) throws ServiceException;

    /**
     * Find menu by dishname (search by part of title). Throws ServiceException if title is null or empty or if reading from data source throws the exception.
     *
     * @param dishname the dishname
     * @return the list of dishname
     * @throws ServiceException if dishname is null or empty or if reading from data source throws the exception
     */
    List<Menu> findMenuByDishName(String dishname) throws ServiceException;

    /**
     * Find menu by categoryid type. Throws ServiceException if menu type is null or if reading from data source throws the exception.
     *
     * @param categoryId the menu type
     * @return the list of menu
     * @throws ServiceException if menu type is null or if reading from data source throws the exception
     */
    List<Menu> findMenuByCategoryId(MenuCategory categoryId) throws ServiceException;
    /**
     * Find dishname of menu. Throws ServiceException if menu is null or if movie does not exist or if reading from data source throws the exception.
     *
     * @param dishname the dishname
     * @return the score of dishname
     * @throws ServiceException if dishname is null or if dishname does not exist or if reading from data source throws the exception
     */
    List<Menu> findDish(Menu dishname) throws ServiceException;
}

package by.popolamov.restourant.model.service;

import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.entity.MenuOrder;
import by.popolamov.restourant.model.entity.MenuOrderQuantity;

import java.util.List;

public interface MenuOrderService {
    /**
     * Add movie. Throws ServiceException if movie is null or if writing to data source throws the exception.
     *
     * @param menuOrder the menu to add
     * @throws ServiceException if menu is null or if writing to data source throws the exception
     */
    void add(MenuOrder menuOrder) throws ServiceException;

    /**
     * Update movie. Throws ServiceException if movie is null or if writing to data source throws the exception.
     *
     * @param menuOrder the updated movie
     * @throws ServiceException if movie is null or if writing to data source throws the exception
     */
    void update(MenuOrder menuOrder) throws ServiceException;

    /**
     * Delete movie. Throws ServiceException if movie is null or if updating of data source throws the exception.
     *
     * @param menuOrder the movie to delete
     * @throws ServiceException if movie is null or if updating of data source throws the exception
     */
    void delete(MenuOrder menuOrder) throws ServiceException;

    /**
     * Find movie by movie id. Throws ServiceException if movie id is null or if reading from data source throws the exception.
     *
     * @param dishid the movie id
     * @return the movie
     * @throws ServiceException if movie id is null or if reading from data source throws the exception
     */
    MenuOrder findMenuByDishId(int dishid) throws ServiceException;
    /**
     * Find movie by movie id. Throws ServiceException if movie id is null or if reading from data source throws the exception.
     *
     * @param dishid the movie id
     * @return the movie
     * @throws ServiceException if movie id is null or if reading from data source throws the exception
     */
    MenuOrder findOrderByUserId(int dishid) throws ServiceException;
    List<MenuOrder> findOrderByOrder(MenuOrder order) throws ServiceException;

    List<MenuOrder> findCartByQuentity(MenuOrderQuantity menuOrderQuantity) throws ServiceException;

}

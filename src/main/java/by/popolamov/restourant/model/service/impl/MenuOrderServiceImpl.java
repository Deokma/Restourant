package by.popolamov.restourant.model.service.impl;

import by.popolamov.restourant.exception.DaoException;
import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.dao.MenuOrderDao;
import by.popolamov.restourant.model.dao.impl.MenuOrderDaoImpl;
import by.popolamov.restourant.model.entity.MenuOrder;
import by.popolamov.restourant.model.service.MenuOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class MenuOrderServiceImpl implements MenuOrderService {
    private static final Logger logger = LogManager.getLogger(MenuServiceImpl.class);
    private final MenuOrderDao menuOrderDao = MenuOrderDaoImpl.getInstance();

    private MenuOrderServiceImpl() {

    }

    private static final class MenuOrderServiceInstanceHolder {
        private static final MenuOrderServiceImpl INSTANCE = new MenuOrderServiceImpl();
    }

    /**
     * Gets instance of movie service.
     *
     * @return the instance of movie service
     */
    public static MenuOrderService getInstance() {
        return MenuOrderServiceImpl.MenuOrderServiceInstanceHolder.INSTANCE;
    }

    @Override
    public void add(MenuOrder menuOrder) throws ServiceException {
        try {
            menuOrderDao.add(menuOrder);
        } catch (DaoException e) {
            logger.error("Can't handle addMenu request at MenuService", e);
            throw new ServiceException("Can't handle addMenu request at MenuService", e);
        }
    }

    @Override
    public void update(MenuOrder menuOrder) throws ServiceException {
        try {
            menuOrderDao.update(menuOrder);
        } catch (DaoException e) {
            logger.error("Can't handle update request at MenuService", e);
            throw new ServiceException("Can't handle update request at MenuService", e);
        }
    }

    @Override
    public void delete(MenuOrder menuOrder) throws ServiceException {
        try {
            menuOrderDao.delete(menuOrder);
        } catch (DaoException e) {
            logger.error("Can't handle deleteMenu request at MenuService", e);
            throw new ServiceException("Can't handle deleteMenu request at MenuService", e);
        }
    }
    @Override
    public MenuOrder findMenuByDishId(int dishid) throws ServiceException {
        if (dishid == 0) {
            logger.error("DishId doesn't present");
            throw new ServiceException("DishId doesn't present");
        }
        try {
            Optional<MenuOrder> optionalMenuOrder = menuOrderDao.findMenuByDishId(dishid);
            if (optionalMenuOrder.isPresent()) {
                return optionalMenuOrder.get();
            } else {
                logger.error("Menu with id={} not found", dishid);
                throw new ServiceException("Menu with id=" + dishid + " not found");
            }
        } catch (DaoException e) {
            logger.error("Can't handle findMenuByDishId request at MenuService", e);
            throw new ServiceException("Can't handle findMenuByDishId request at MenuService", e);
        }
    }
    @Override
    public MenuOrder findOrderByUserId(int orderid) throws ServiceException {
        if (orderid == 0) {
            logger.error("DishId doesn't present");
            throw new ServiceException("DishId doesn't present");
        }
        try {
            Optional<MenuOrder> optionalMenuOrder = menuOrderDao.findMenuOrderByUserId(orderid);
            if (optionalMenuOrder.isPresent()) {
                return optionalMenuOrder.get();
            } else {
                logger.error("Menu with id={} not found", orderid);
                throw new ServiceException("Menu with id=" + orderid + " not found");
            }
        } catch (DaoException e) {
            logger.error("Can't handle findMenuByDishId request at MenuService", e);
            throw new ServiceException("Can't handle findMenuByDishId request at MenuService", e);
        }
    }
    @Override
    public List<MenuOrder> findOrderByOrder(MenuOrder menuOrder) throws ServiceException {
        if (menuOrder == null) {
            logger.error("orderStatus doesn't present");
            throw new ServiceException("orderStatus doesn't present");
        }

        List<MenuOrder> orders;
        try {
            orders = menuOrderDao.findOrderByOrder(menuOrder);
        } catch (DaoException e) {
            logger.error("Can't handle findOrderByOrderStatus request at MenuService", e);
            throw new ServiceException("Can't handle findOrderByOrderStatus request at MenuService", e);
        }
        return orders;
    }
//    @Override
//    public MenuOrder findByAll() throws ServiceException {
//        try {
//            Optional<MenuOrder> optionalMenuOrder = menuOrderDao.findMenu(orderid);
//            if (optionalMenuOrder.isPresent()) {
//                return optionalMenuOrder.get();
//            } else {
//                logger.error("Menu with id={} not found", orderid);
//                throw new ServiceException("Menu with id=" + orderid + " not found");
//            }
//        } catch (DaoException e) {
//            logger.error("Can't handle findMenuByDishId request at MenuService", e);
//            throw new ServiceException("Can't handle findMenuByDishId request at MenuService", e);
//        }
//    }
//    @Override
//    public List<Menu> findMenuByCategoryId(MenuCategory categoryid) throws ServiceException {
//        if (categoryid == null) {
//            logger.error("CategoryId doesn't present");
//            throw new ServiceException("CategoryId doesn't present");
//        }
//
//        List<Menu> menus;
//        try {
//            menus = menuOrderDao.findMenuByCategoryId(categoryid);
//        } catch (DaoException e) {
//            logger.error("Can't handle findMenuByCategoryId request at MenuService", e);
//            throw new ServiceException("Can't handle findMenuByCategoryId request at MenuService", e);
//        }
//        return menus;
//    }

//    @Override
//    public List<Menu> findMenuByDishName(String dishName) throws ServiceException {
//        if (dishName == null || dishName.isEmpty()) {
//            logger.error("Title doesn't present");
//            throw new ServiceException("Title doesn't present");
//        }
//        List<Menu> menus;
//        try {
//            menus = menuDao.findMenuByDishName(dishName);
//        } catch (DaoException e) {
//            logger.error("Can't handle findMenuByDishName request at MenuService", e);
//            throw new ServiceException("Can't handle findMenuByDishName request at MenuService", e);
//        }
//        return menus;
//    }

}

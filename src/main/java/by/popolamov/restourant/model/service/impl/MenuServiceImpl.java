package by.popolamov.restourant.model.service.impl;

import by.popolamov.restourant.exception.DaoException;
import by.popolamov.restourant.exception.ServiceException;

import by.popolamov.restourant.model.dao.MenuDao;
import by.popolamov.restourant.model.dao.impl.MenuDaoImpl;
import by.popolamov.restourant.model.entity.Menu;
import by.popolamov.restourant.model.entity.MenuCategory;
import by.popolamov.restourant.model.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * The implementation of MovieService interface.
 */
public class MenuServiceImpl implements MenuService {
    private static final Logger logger = LogManager.getLogger(MenuServiceImpl.class);
    private final MenuDao menuDao = MenuDaoImpl.getInstance();
    //private final GenreDao genreDao = GenreDaoImpl.getInstance();

    private MenuServiceImpl() {

    }

    private static final class MenuServiceInstanceHolder {
        private static final MenuServiceImpl INSTANCE = new MenuServiceImpl();
    }

    /**
     * Gets instance of movie service.
     *
     * @return the instance of movie service
     */
    public static MenuService getInstance() {
        return MenuServiceInstanceHolder.INSTANCE;
    }

    @Override
    public void addMenu(Menu menu) throws ServiceException {
        try {
            menuDao.add(menu);
        } catch (DaoException e) {
            logger.error("Can't handle addMenu request at MenuService", e);
            throw new ServiceException("Can't handle addMenu request at MenuService", e);
        }
    }

    @Override
    public void update(Menu menu) throws ServiceException {
        try {
            menuDao.update(menu);
        } catch (DaoException e) {
            logger.error("Can't handle update request at MenuService", e);
            throw new ServiceException("Can't handle update request at MenuService", e);
        }
    }

    @Override
    public void deleteMenu(Menu menu) throws ServiceException {
        try {
            menuDao.delete(menu);
        } catch (DaoException e) {
            logger.error("Can't handle deleteMenu request at MenuService", e);
            throw new ServiceException("Can't handle deleteMenu request at MenuService", e);
        }
    }

    @Override
    public List<Menu> findDish(Menu dishname) throws ServiceException {
        return findMenuByDishName(dishname.getDishName());
    }

    @Override
    public Menu findMenuByDishId(int dishid) throws ServiceException {
        if (dishid == 0) {
            logger.error("DishId doesn't present");
            throw new ServiceException("DishId doesn't present");
        }
        try {
            Optional<Menu> optionalMenu = menuDao.findMenuByDishId(dishid);
            if (optionalMenu.isPresent()) {
                return optionalMenu.get();
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
    public List<Menu> findMenuByCategoryId(MenuCategory categoryid) throws ServiceException {
        if (categoryid == null) {
            logger.error("CategoryId doesn't present");
            throw new ServiceException("CategoryId doesn't present");
        }

        List<Menu> menus;
        try {
            menus = menuDao.findMenuByCategoryId(categoryid);
        } catch (DaoException e) {
            logger.error("Can't handle findMenuByCategoryId request at MenuService", e);
            throw new ServiceException("Can't handle findMenuByCategoryId request at MenuService", e);
        }
        return menus;
    }

    @Override
    public List<Menu> findMenuByDishName(String dishName) throws ServiceException {
        if (dishName == null || dishName.isEmpty()) {
            logger.error("Title doesn't present");
            throw new ServiceException("Title doesn't present");
        }
        List<Menu> menus;
        try {
            menus = menuDao.findMenuByDishName(dishName);
        } catch (DaoException e) {
            logger.error("Can't handle findMenuByDishName request at MenuService", e);
            throw new ServiceException("Can't handle findMenuByDishName request at MenuService", e);
        }
        return menus;
    }
}

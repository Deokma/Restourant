package by.popolamov.restourant.controller.command.impl.moveto.general;

import by.popolamov.restourant.controller.command.Command;
import by.popolamov.restourant.controller.command.PagePath;
import by.popolamov.restourant.controller.command.RequestAttribute;
import by.popolamov.restourant.controller.command.Router;
import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.entity.Menu;
import by.popolamov.restourant.model.entity.MenuCategory;
//import by.popolamov.restourant.model.entity.Movie;
//import by.popolamov.restourant.model.entity.MovieType;
//import by.popolamov.restourant.model.service.MovieService;
//import by.popolamov.restourant.model.service.impl.MovieServiceImpl;
import by.popolamov.restourant.model.service.MenuService;
import by.popolamov.restourant.model.service.impl.MenuServiceImpl;
import by.popolamov.restourant.util.ImageInputStreamUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MoveToMainPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MoveToMainPageCommand.class);
    private static final MenuService menuService = MenuServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            List<Menu> menuList = new ArrayList<>();
            menuList.addAll(menuService.findMenuByCategoryId(MenuCategory.FOOD));
            menuList.addAll(menuService.findMenuByCategoryId(MenuCategory.DRINKS));
            List<Menu> menuDishList = new ArrayList<>();
            for (Menu menu : menuList) {
                menuDishList.addAll(menuService.findDish(menu));
            }
            request.setAttribute(RequestAttribute.MENU_LIST, menuList);
            request.setAttribute(RequestAttribute.MENU_PRICE_LIST, menuDishList);
        } catch (ServiceException e) {
            logger.error("Error at MoveToMainPageCommand", e);
        }
        return new Router(PagePath.MAIN_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}

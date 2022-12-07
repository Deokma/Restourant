package by.popolamov.restourant.controller.command.impl.moveto.admin;

import by.popolamov.restourant.controller.command.Command;
import by.popolamov.restourant.controller.command.PagePath;
import by.popolamov.restourant.controller.command.Router;
import by.popolamov.restourant.model.service.MenuService;
import by.popolamov.restourant.model.service.impl.MenuServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class MoveToCartPageCommand.
 */
public class MoveToOrderPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MoveToOrderPageCommand.class);
    private static final MenuService menuService = MenuServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
//        Router router;
//        RequestUtil requestUtil = RequestUtil.getInstance();
//        try {
//            int dishid = requestUtil.getParameterAsInt(request, RequestParameter.DISH_ID);
//            Menu menu = menuService.findMenuByDishId(dishid);
//            request.setAttribute(RequestAttribute.MENU, menu);
//            router = new Router(PagePath.CART_PAGE.getAddress(), Router.RouterType.FORWARD);
//        } catch (ServiceException e) {
//            logger.error("Error at MoveToAddFeedbackPageCommand", e);
//            String pageTo = getPageFrom(request);
//            router = new Router(pageTo, Router.RouterType.REDIRECT);
//        }
//        return router;
        return new Router(PagePath.ORDERS_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}

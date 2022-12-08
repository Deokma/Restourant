package by.popolamov.restourant.controller.command.impl.user;

import by.popolamov.restourant.controller.command.*;
import by.popolamov.restourant.controller.command.RequestUtil;
import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.entity.*;
import by.popolamov.restourant.model.service.MenuOrderService;
import by.popolamov.restourant.model.service.MenuService;
import by.popolamov.restourant.model.service.impl.MenuOrderServiceImpl;
import by.popolamov.restourant.model.service.impl.MenuServiceImpl;
import by.popolamov.restourant.util.ImageInputStreamUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The class AddFeedbackCommand.
 */
public class AddDishToCartCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddDishToCartCommand.class);
    private static final MenuOrderService menuOrderService = MenuOrderServiceImpl.getInstance();
    private static final MenuService menuService = MenuServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            int dishid = requestUtil.getParameterAsInt(request, RequestParameter.DISH_ID);
            int userid = requestUtil.getParameterAsInt(request, SessionAttribute.USER_ID);
            int price = requestUtil.getParameterAsInt(request, RequestParameter.PRICE);
            User user = (User) request.getSession().getAttribute(SessionAttribute.USER);

            MenuOrder menuOrder = buildCart(user,dishid,price);
            menuOrderService.add(menuOrder);
            router = new Router(PagePath.INDEX_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at AddDishToCartCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }
        return router;
    }

    private MenuOrder buildCart(User user, int dishid, int price) {
        return MenuOrder.builder()
                .setUserid(user.getUserId())
                .setDishId(dishid)
                .setPrice(price)
                .build();
    }
}

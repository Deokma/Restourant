package by.popolamov.restourant.controller.command.impl.user;

import by.popolamov.restourant.controller.command.*;
import by.popolamov.restourant.controller.command.Router.RouterType;
import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.entity.MenuOrder;
import by.popolamov.restourant.model.entity.MenuOrderQuantity;
import by.popolamov.restourant.model.service.MenuOrderService;
import by.popolamov.restourant.model.service.impl.MenuOrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CompleteCartCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CompleteCartCommand.class);
    private static final MenuOrderService menuOrderService = MenuOrderServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            int userid = requestUtil.getParameterAsInt(request, RequestParameter.USER_ID);
            request.getSession().getAttribute(SessionAttribute.USER_ID);
            MenuOrder menuOrder = sendCart(userid);
            menuOrderService.update(menuOrder);
            MenuOrder menuOrder1 = sendCart(userid);
            menuOrderService.delete(menuOrder1);
//            List<MenuOrder> menuOrderList = new ArrayList<>();
//            menuOrderList.addAll(menuOrderService.findCartByQuentity(MenuOrderQuantity.ONE));
//            request.setAttribute(RequestAttribute.MENU_ORDER, menuOrderList);
            router = new Router(PagePath.INDEX_PAGE.getAddress(), RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at CompleteCartCommand", e);
            router = new Router(PagePath.CART_PAGE.getAddress(), Router.RouterType.FORWARD);
        }
        return router;
    }

    public MenuOrder sendCart(int userid) {
        return MenuOrder.builder()
                .setUserid(userid)
                .build();
    }
}

//package by.popolamov.restourant.controller.command.impl.general;
//
//import by.popolamov.restourant.controller.command.Command;
//import by.popolamov.restourant.controller.command.Router;
//import by.popolamov.restourant.controller.command.SessionAttribute;
//import jakarta.servlet.http.HttpServletRequest;
//
///**
// * The class ChangeLocaleCommand.
// */
//public class ChangeLocaleCommand implements Command {
//    private static final String EN_LOCALE = "en_EN";
//    private static final String RU_LOCALE = "ru_RU";
//
//    @Override
//    public Router execute(HttpServletRequest request) {
//        String currentLocale = (String) request.getSession().getAttribute(SessionAttribute.LOCALE);
//        if (currentLocale.equals(RU_LOCALE)) {
//            setNewLocale(request, EN_LOCALE);
//        } else {
//            setNewLocale(request, RU_LOCALE);
//        }
//        String pageTo = getPageFrom(request);
//        return new Router(pageTo, Router.RouterType.REDIRECT);
//    }
//
//    private void setNewLocale(HttpServletRequest request, String newLocale) {
//        request.getSession().removeAttribute(SessionAttribute.LOCALE);
//        request.getSession().setAttribute(SessionAttribute.LOCALE, newLocale);
//    }
//}

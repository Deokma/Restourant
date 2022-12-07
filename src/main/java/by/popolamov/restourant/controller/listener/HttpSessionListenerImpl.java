package by.popolamov.restourant.controller.listener;

import by.popolamov.restourant.controller.command.SessionAttribute;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * The Http session listener. It sets locale when session creates.
 */
@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute(SessionAttribute.LOCALE, "ru_RU");
    }
}

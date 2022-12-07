package by.radzionau.imdb.controller.listener;

import by.radzionau.imdb.model.pool.CustomConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * The Servlet context listener. It initializes the connection pool when the application starts and destroys the connection pool when the application shuts down.
 */
@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        CustomConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        CustomConnectionPool.getInstance().destroyPull();
    }
}

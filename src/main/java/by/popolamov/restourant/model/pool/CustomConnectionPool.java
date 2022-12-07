package by.popolamov.restourant.model.pool;
import by.popolamov.restourant.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The class CustomConnectionPool provides connections to database.
 */
public class CustomConnectionPool {
    private static final Logger logger = LogManager.getLogger(CustomConnectionPool.class);
    private static CustomConnectionPool instance;
    private static final int DEFAULT_POOL_SIZE = 8;
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private final BlockingDeque<Connection> freeConnections;
    private final BlockingDeque<Connection> busyConnections;

    private CustomConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        busyConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            ProxyConnection connection;
            try {
                connection = ConnectionFactory.createConnection();
                freeConnections.offer(connection);
            } catch (SQLException e) {
                logger.error("Connection can't be created.", e);
            }
        }
        if (freeConnections.isEmpty()) {
            logger.fatal("Connections pool can't be created. Database access error");
            throw new RuntimeException("Connections pool can't be created. Database access error");
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance of connection pool
     */
    public static CustomConnectionPool getInstance() {
        while (instance == null) {
            if (isInitialized.compareAndSet(false, true)) {
                instance = new CustomConnectionPool();
            }
        }
        return instance;
    }

    /**
     * Gets connection. Throws ConnectionPoolException if it cannot take connection from pool or if time out for getting connection.
     *
     * @return the connection
     * @throws ConnectionPoolException if it cannot take connection from pool or if time out for getting connection
     */
    public Connection getConnection() throws ConnectionPoolException {
        try {
            Connection connection = freeConnections.take();
            busyConnections.put(connection);
            return connection;
        } catch (InterruptedException e) {
            logger.error("Cannot take or put connection: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        throw new ConnectionPoolException("Time out for getting connection");
    }

    /**
     * Release connection.
     *
     * @param connection the connection
     * @return the true if connection released successfully and false if not.
     */
    public boolean releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            ProxyConnection proxyConnection = (ProxyConnection) connection;
            try {
                if (busyConnections.remove(proxyConnection)) {
                    freeConnections.put(proxyConnection);
                } else {
                    logger.error("Can't put connection in pool connection because connection isn't valid. {}", proxyConnection);
                }
            } catch (InterruptedException e) {
                logger.error("Cannot put connection", e);
                Thread.currentThread().interrupt();
                return false;
            }
            return true;
        } else {
            logger.error("Wild connection: {}", connection);
            return false;
        }
    }

    /**
     * Destroy pull, release all connections.
     */
    public void destroyPull() {
        int countOfBusyConnections = busyConnections.size();
        for (int i = 0; i < countOfBusyConnections; i++) {
            try {
                Connection connection = busyConnections.take();
                freeConnections.put(connection);
            } catch (InterruptedException e) {
                logger.error("Cannot take connection", e);
                Thread.currentThread().interrupt();
            }
        }

        int countOfFreeConnections = freeConnections.size();
        for (int i = 0; i < countOfFreeConnections; i++) {
            try {
                ProxyConnection proxyConnection = (ProxyConnection) freeConnections.take();
                proxyConnection.reallyClose();
            } catch (InterruptedException e) {
                logger.error("Cannot take connection", e);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        try {
            while (drivers.hasMoreElements()) {
                DriverManager.deregisterDriver(drivers.nextElement());
            }
        } catch (SQLException e) {
            logger.warn("Cannot deregister driver", e);
        }
    }
}


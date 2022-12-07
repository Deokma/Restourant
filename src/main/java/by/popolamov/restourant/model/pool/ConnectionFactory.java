package by.popolamov.restourant.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The class ConnectionFactory creates connections.
 */
class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class);
    private static final String FILE_PROPERTIES_NAME = "database/db.properties";
    private static final String URL_ATTRIBUTE_NAME = "db.url";
    private static final String DRIVER_ATTRIBUTE_NAME = "db.driver";

    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;
    private static String driverName;

    static {
        try {
            URL fileURL = ConnectionFactory.class.getClassLoader().getResource(FILE_PROPERTIES_NAME);
            if (fileURL == null) {
                logger.fatal("File can't be found for the specified name  = {}", FILE_PROPERTIES_NAME);
                throw new ExceptionInInitializerError("File can't be found for the specified name  = " + FILE_PROPERTIES_NAME);
            }
            properties.load(new FileReader(fileURL.getFile()));
            driverName = (String) properties.get(DRIVER_ATTRIBUTE_NAME);
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            logger.fatal("Driver can't be set. Driver = {}", driverName);
            throw new ExceptionInInitializerError("Driver can't be set. Driver = " + driverName);
        } catch (IOException e) {
            logger.fatal("File can't be read. File = {}", driverName);
            throw new ExceptionInInitializerError("File can't be read. File = " + driverName);
        }
        DATABASE_URL = (String) properties.get(URL_ATTRIBUTE_NAME);
        if (DATABASE_URL == null) {
            logger.fatal("Missing database url in the file = {}", FILE_PROPERTIES_NAME);
            throw new ExceptionInInitializerError("Missing database url in the file = " + FILE_PROPERTIES_NAME);
        }
    }

    private ConnectionFactory() {

    }

    /**
     * Create connection ProxyConnection. Throws SQLException if a database access error occurs.
     *
     * @return the proxy connection
     * @throws SQLException if a database access error occurs
     */
    static ProxyConnection createConnection() throws SQLException {
        return new ProxyConnection(DriverManager.getConnection(DATABASE_URL, properties));
    }
}

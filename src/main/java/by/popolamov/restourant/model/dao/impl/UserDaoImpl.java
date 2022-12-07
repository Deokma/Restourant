package by.popolamov.restourant.model.dao.impl;

import by.popolamov.restourant.exception.ConnectionPoolException;
import by.popolamov.restourant.exception.DaoException;
import by.popolamov.restourant.model.dao.UserDao;
import by.popolamov.restourant.model.entity.User;
import by.popolamov.restourant.model.entity.UserRole;
import by.popolamov.restourant.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The implementation of UserDao interface.
 */
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();

    private static final String SQL_INSERT_USER =
            "INSERT INTO users (login, password, firstname, lastname, roleid) " +
                    "VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE_USER =
            "UPDATE users SET login=?, firstname=?, lastname=?, roleid=?," +
                    "WHERE userid=?";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT userid, login, firstname, lastname, roleid.name AS role_name " +
                    "FROM users " +
                    "JOIN roles ON roles.roleid=user.roleid " +
                    "WHERE userid=?";
    private static final String SQL_SELECT_USER_BY_LOGIN =
            "SELECT userid, login, firstName, lastName, roleid FROM users" +
            " WHERE login=?";
    private static final String SQL_SELECT_USER_PASSWORD_BY_LOGIN =
            "SELECT password " +
                    "FROM users " +
                    "WHERE login=?";
    private static final String SQL_SELECT_USERS_BY_ROLE =
            "SELECT userid, login, firstName, lastName, roles.name AS role_name " +
                    "FROM users " +
                    "JOIN roles ON roles.roleid=users.roleid " +
                    "WHERE users.roleid=?";
    private static final String SQL_SELECT_ALL_USERS =
            "SELECT user, login, firstName, lastName, roles.name AS role_name " +
                    "FROM users " +
                    "JOIN roles ON roles.roleid=users.userid ";

    private static final class MySqlUserDaoInstanceHolder {
        private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    }

    /**
     * Gets instance.
     *
     * @return the instance of user dao
     */
    public static UserDaoImpl getInstance() {
        return MySqlUserDaoInstanceHolder.INSTANCE;
    }

    @Override
    public int add(User user, String hashedPassword) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, user.getLogin());
            statement.setString(2, hashedPassword);
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setLong(5, user.getRole().getId());
            int rowsUpdate = statement.executeUpdate();
            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int key = resultSet.getInt(1);
                    user.setUserId(key);
                }
            }
            return rowsUpdate;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while adding a user={}", user, e);
            throw new DaoException("Error while adding a user=" + user, e);
        }
    }

    @Override
    public int update(User user) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)
        ) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setLong(4, user.getRole().getId());
            statement.setLong(5, user.getUserId());
            return statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while updating a user={}", user, e);
            throw new DaoException("Error while updating a user=" + user, e);
        }
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN)
        ) {
            statement.setString(1, login);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(createUser(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a user", e);
            throw new DaoException("Error while selecting a user", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> findUserPasswordByLogin(String login) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_PASSWORD_BY_LOGIN)
        ) {
            statement.setString(1, login);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String password = resultSet.getString(1);
                    return Optional.of(password);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a password", e);
            throw new DaoException("Error while selecting a password", e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_USERS);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a users", e);
            throw new DaoException("Error while selecting a users", e);
        }
        return users;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .setUserId(resultSet.getInt(1))
                .setLogin(resultSet.getString(2))
                .setFirstName(resultSet.getString(3))
                .setLastName(resultSet.getString(4))
                .setRole(UserRole.getById(resultSet.getInt(5)))
                //.setRole(UserRole.valueOf(resultSet.getString(5).toUpperCase()))
                .build();
    }
}

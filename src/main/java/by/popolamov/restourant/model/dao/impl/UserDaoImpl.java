package by.radzionau.imdb.model.dao.impl;

import by.radzionau.imdb.exception.ConnectionPoolException;
import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.model.dao.UserDao;
import by.radzionau.imdb.model.entity.User;
import by.radzionau.imdb.model.entity.UserRole;
import by.radzionau.imdb.model.entity.UserStatus;
import by.radzionau.imdb.model.pool.CustomConnectionPool;
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
            "INSERT INTO usr (login, password, mail, first_name, surname, usr_role_id, usr_status_id) " +
                    "VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_USER =
            "UPDATE usr SET login=?, mail=?, first_name=?, surname=?, usr_role_id=?, usr_status_id=? " +
                    "WHERE usr_id=?";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT usr_id, login, mail, first_name, surname, usr_role.name AS role_name, usr_status.name AS user_status " +
                    "FROM usr " +
                    "JOIN usr_role ON usr_role.usr_role_id=usr.usr_role_id " +
                    "JOIN usr_status ON usr_status.usr_status_id=usr.usr_status_id " +
                    "WHERE usr_id=?";
    private static final String SQL_SELECT_USER_BY_LOGIN =
            "SELECT usr_id, login, mail, first_name, surname, usr_role.name AS role_name, usr_status.name AS user_status " +
                    "FROM usr " +
                    "JOIN usr_role ON usr_role.usr_role_id=usr.usr_role_id " +
                    "JOIN usr_status ON usr_status.usr_status_id=usr.usr_status_id " +
                    "WHERE login=?";
    private static final String SQL_SELECT_USER_PASSWORD_BY_LOGIN =
            "SELECT password " +
                    "FROM usr " +
                    "WHERE login=?";
    private static final String SQL_SELECT_USERS_BY_STATUS =
            "SELECT usr_id, login, mail, first_name, surname, usr_role.name AS role_name, usr_status.name AS user_status " +
                    "FROM usr " +
                    "JOIN usr_role ON usr_role.usr_role_id=usr.usr_role_id " +
                    "JOIN usr_status ON usr_status.usr_status_id=usr.usr_status_id " +
                    "WHERE usr.usr_status_id=?";
    private static final String SQL_SELECT_USERS_BY_ROLE =
            "SELECT usr_id, login, mail, first_name, surname, usr_role.name AS role_name, usr_status.name AS user_status " +
                    "FROM usr " +
                    "JOIN usr_role ON usr_role.usr_role_id=usr.usr_role_id " +
                    "JOIN usr_status ON usr_status.usr_status_id=usr.usr_status_id " +
                    "WHERE usr.usr_role_id=?";
    private static final String SQL_SELECT_ALL_USERS =
            "SELECT usr_id, login, mail, first_name, surname, usr_role.name AS role_name, usr_status.name AS user_status " +
                    "FROM usr " +
                    "JOIN usr_role ON usr_role.usr_role_id=usr.usr_role_id " +
                    "JOIN usr_status ON usr_status.usr_status_id=usr.usr_status_id ";

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
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSurname());
            statement.setLong(6, user.getRole().getId());
            statement.setLong(7, user.getStatus().getId());
            int rowsUpdate = statement.executeUpdate();
            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    Long key = resultSet.getLong(1);
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
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setLong(5, user.getRole().getId());
            statement.setLong(6, user.getStatus().getId());
            statement.setLong(7, user.getUserId());
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
                .setUserId(resultSet.getLong(1))
                .setLogin(resultSet.getString(2))
                .setEmail(resultSet.getString(3))
                .setName(resultSet.getString(4))
                .setSurname(resultSet.getString(5))
                .setRole(UserRole.valueOf(resultSet.getString(6).toUpperCase()))
                .setStatus(UserStatus.valueOf(resultSet.getString(7).toUpperCase()))
                .build();
    }
}

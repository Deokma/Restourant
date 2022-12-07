package by.radzionau.imdb.model.dao;

import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface describes the behavior of {@link User} entity
 *
 * @author Anton Radzionau
 */
public interface UserDao {
    /**
     * Add user. Throws DaoException if writing to data source throws exception.
     *
     * @param user           the user to add
     * @param hashedPassword the hashed password
     * @return the count of updated rows
     * @throws DaoException if writing to data source throws exception
     */
    int add(User user, String hashedPassword) throws DaoException;

    /**
     * Update user. Throws DaoException if writing to data source throws exception.
     *
     * @param user the updated user
     * @return the count of updated rows
     * @throws DaoException if writing to data source throws exception
     */
    int update(User user) throws DaoException;

    /**
     * Find user by login. Throws DaoException if reading of data source throws exception.
     *
     * @param login the login
     * @return the optional of user
     * @throws DaoException if reading of data source throws exception
     */
    Optional<User> findUserByLogin(String login) throws DaoException;

    /**
     * Find user password by login. Throws DaoException if reading of data source throws exception.
     *
     * @param login the login
     * @return the optional of user
     * @throws DaoException if reading of data source throws exception
     */
    Optional<String> findUserPasswordByLogin(String login) throws DaoException;

    /**
     * Find all users. Throws DaoException if reading of data source throws exception.
     *
     * @return the list of users
     * @throws DaoException if reading of data source throws exception
     */
    List<User> findAll() throws DaoException;
}

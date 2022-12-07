package by.popolamov.restourant.model.service;

import by.popolamov.restourant.exception.ServiceException;
import by.popolamov.restourant.model.entity.User;
import by.popolamov.restourant.model.entity.UserRole;

import java.util.List;

/**
 * The interface UserService provides methods to manage users.
 */
public interface UserService {
    /**
     * Sign in user. Throws ServiceException if params is null or empty or if user with specified params does not exist or if reading from data source throws exception.
     *
     * @param login    the login
     * @param password the password
     * @return the user
     * @throws ServiceException if params is null or empty or if user with specified params does not exist or if reading from data source throws exception
     */
    User signIn(String login, String password) throws ServiceException;

    /**
     * Sign up user. Throws ServiceException if params is null or empty or if writing to data source throws exception.
     *
     * @param login            the login
     * @param password         the password
     * @param repeatedPassword the repeated password
     * @param firstName        the first name
     * @param lastName          the lastName
     * @return the user
     * @throws ServiceException if params is null or empty or if writing to data source throws exception
     */
    User signUp(String login, String password, String repeatedPassword, String firstName, String lastName) throws ServiceException;

    /**
     * Update role of user. Throws ServiceException if params is null or empty or if writing to data source throws exception.
     *
     * @param user     the user
     * @param userRole the user role
     * @return the updated user
     * @throws ServiceException if params is null or empty or if writing to data source throws exception
     */
    User updateRole(User user, UserRole userRole) throws ServiceException;

    /**
     * Find user by user login. Throws ServiceException if login is null or if user does not exist or if reading of data source throws exception.
     *
     * @param login the login
     * @return the user
     * @throws ServiceException if login is null or if user does not exist or if reading of data source throws exception
     */
    User findUserByLogin(String login) throws ServiceException;

    /**
     * Find all users. Throws ServiceException if reading of data source throws exception.
     *
     * @return the list of users
     * @throws ServiceException if reading of data source throws exception
     */
    List<User> findAll() throws ServiceException;
}

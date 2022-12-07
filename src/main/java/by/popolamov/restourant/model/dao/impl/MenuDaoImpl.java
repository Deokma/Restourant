package by.popolamov.restourant.model.dao.impl;

import by.popolamov.restourant.exception.ConnectionPoolException;
import by.popolamov.restourant.exception.DaoException;
import by.popolamov.restourant.model.dao.MenuDao;
import by.popolamov.restourant.model.entity.Menu;
import by.popolamov.restourant.model.entity.MenuCategory;
import by.popolamov.restourant.model.entity.Order;
import by.popolamov.restourant.model.pool.CustomConnectionPool;
import by.popolamov.restourant.util.ImageInputStreamUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The implementation of MovieDao interface.
 */
public class MenuDaoImpl implements MenuDao {
    private static final Logger logger = LogManager.getLogger(MenuDaoImpl.class);
    private static final ImageInputStreamUtil inputStreamUtil = ImageInputStreamUtil.getInstance();
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();

    private static final String SQL_INSERT_MENU =
            "INSERT INTO menu (dishname, price, categoryid) " +
                    "VALUES (?,?,?)";
    private static final String SQL_UPDATE_MENU =
            "UPDATE menu SET dishname=?, price=?, categoryid=?" +
                    "WHERE dishid=?";
    private static final String SQL_DELETE_MENU =
            "DELETE FROM menu " +
                    "WHERE dishid=?";
    private static final String SQL_SELECT_BY_CATEGORY_ID =
            "SELECT dishid,dishname, price, categoryid,image FROM menu WHERE categoryid=?";
    private static final String SQL_SELECT_BY_DISH_ID =
            "SELECT dishname, price, categoryid, dishid,image FROM menu WHERE dishid=?";
    private static final String SQL_SELECT_BY_DISH_NAME =
            "SELECT dishname, price, categoryid, dishid,image FROM menu WHERE dishname=?";
    private static final String SQL_SELECT_MOVIES_BY_MOVIE_TYPE =
            "SELECT movie_id, title, logline, release_year, cover, movie_type.name AS movie_type " +
                    "FROM movie " +
                    "JOIN movie_type ON movie.movie_type_id=movie_type.movie_type_id " +
                    "WHERE movie.movie_type_id=?";
    private static final String SQL_CALCULATE_MENU_PRICE_BY_DISH_ID =
            "SELECT SUM(price) AS totalsum " +
                    "FROM order " +
                    "WHERE dishid=?";

    private MenuDaoImpl() {

    }

    private static final class MySqlMenuDaoInstanceHolder {
        private static final MenuDao INSTANCE = new MenuDaoImpl();
    }

    /**
     * Gets instance.
     *
     * @return the instance of movie dao
     */
    public static MenuDao getInstance() {
        return MenuDaoImpl.MySqlMenuDaoInstanceHolder.INSTANCE;
    }

    @Override
    public int add(Menu menu) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_MENU, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, menu.getDishName());
            statement.setInt(2, menu.getPrice());
            statement.setString(3, menu.getImage());
            statement.setInt(4, menu.getCategoryid());
            int rowsUpdate = statement.executeUpdate();
            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int key = resultSet.getInt(1);
                    menu.setDishid(key);
                }
            }
            return rowsUpdate;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while adding a movie", e);
            throw new DaoException("Error while adding a movie", e);
        }
    }

    @Override
    public int update(Menu menu) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_MENU)
        ) {
            statement.setString(1, menu.getDishName());
            statement.setInt(2, menu.getPrice());
            statement.setString(3, menu.getImage());
            statement.setInt(4, menu.getCategoryid());
            statement.setLong(5, menu.getDishid());
            return statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while updating a movie", e);
            throw new DaoException("Error while updating a movie", e);
        }
    }

    @Override
    public int delete(Menu menu) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE_MENU)
        ) {
            statement.setLong(1, menu.getDishid());
            return statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while updating a movie", e);
            throw new DaoException("Error while updating a movie", e);
        }
    }

    @Override
    public List<Menu> findMenuByCategoryId(MenuCategory categoryid) throws DaoException {
        List<Menu> menus = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_CATEGORY_ID)
        ) {
            statement.setInt(1, categoryid.toInt());
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    menus.add(createMenu(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a menu", e);
            throw new DaoException("Error while selecting a menu", e);
        }
        return menus;
    }

    @Override
    public Optional<Menu> findMenuByDishId(int dishid) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_DISH_ID)
        ) {
            statement.setLong(1, dishid);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Menu menu = createMenu(resultSet);
                    return Optional.of(menu);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a movie", e);
            throw new DaoException("Error while selecting a movie", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Menu> findMenuByDishName(String dishname) throws DaoException {
        List<Menu> menu = new ArrayList<>();
        dishname = "%" + dishname + "%";
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_DISH_NAME)
        ) {
            statement.setString(1, dishname);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    menu.add(createMenu(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a movie", e);
            throw new DaoException("Error while selecting a movie", e);
        }
        return menu;
    }

    private Menu createMenu(ResultSet resultSet) throws SQLException {
        return Menu.builder()
                .setDishId(resultSet.getInt(1))
                .setDishName(resultSet.getString(2))
                .setPrice(resultSet.getInt(3))
                .setCategoryId(resultSet.getInt(4))
                .setImage(resultSet.getString(5))
                .build();
    }
}

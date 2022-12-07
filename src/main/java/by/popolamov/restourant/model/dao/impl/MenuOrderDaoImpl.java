package by.popolamov.restourant.model.dao.impl;

import by.popolamov.restourant.exception.ConnectionPoolException;
import by.popolamov.restourant.exception.DaoException;
import by.popolamov.restourant.model.dao.MenuDao;
import by.popolamov.restourant.model.dao.MenuOrderDao;
import by.popolamov.restourant.model.entity.Menu;
import by.popolamov.restourant.model.entity.MenuOrder;
import by.popolamov.restourant.model.entity.Order;
import by.popolamov.restourant.model.entity.User;
import by.popolamov.restourant.model.pool.CustomConnectionPool;
import by.popolamov.restourant.util.ImageInputStreamUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuOrderDaoImpl implements MenuOrderDao {
    private static final Logger logger = LogManager.getLogger(MenuDaoImpl.class);
    private static final ImageInputStreamUtil inputStreamUtil = ImageInputStreamUtil.getInstance();
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();


    private static final String SQL_INSERT_MENU_ORDER =
            "INSERT INTO menuorder (dishid, userid, quentity, price,dishname) " +
                    "SELECT " +
                    "menu.dishid AS dishid, " +
                    "users.userid AS userid, " +
                    "1 AS quantity, " +
                    "menu.price AS price, " +
                    "menu.dishname AS dishname " +
                    "FROM users, menu " +
                    "WHERE userid=? AND dishid=? ";
    private static final String SQL_UPDATE_MENU_ORDER =
            "UPDATE menuorder SET quantity=?, dishname=? " +
                    "WHERE dishid=?";
    private static final String SQL_DELETE_MENU_ORDER =
            "DELETE FROM menuorder " +
                    "WHERE dishid=?";
    private static final String SQL_SELECT_BY_DISH_ID =
            "SELECT dishid, dishname FROM menu WHERE dishid=?";
    private static final String SQL_SELECT_BY_ORDER_ID =
            "SELECT dishid, dishname FROM menu WHERE userid=?";
    private static final String SQL_SELECT_BY_USER_ID =
            "SELECT dishname, price FROM menuorder WHERE userid=?";
    private static final String SQL_SELECT_ALL =
            "SELECT dishname, price FROM menuorder";
    private static final String SQL_SEND_ORDER =
            "INSERT INTO \"order\" (orderid, userid, totalsum, status) " +
                    "SELECT max(orderid)+1, userid, SUM(price), 2 " +
                    "FROM menuorder " +
                    "WHERE userid=? " +
                    "GROUP BY orderid ";
//    private static final String SQL_CALCULATE_ORDER_2 =
//            "SELECT SUM(price) AS totalsum " +
//                    "FROM order " +
//                    "WHERE userid=?";
//    private static final String SQL_CALCULATE_ORDER_3 =
//            "SELECT SUM(price) AS totalsum " +
//                    "FROM order " +
//                    "WHERE userid=?";
//    private static final String SQL_CALCULATE_ORDER_4 =
//            "SELECT SUM(price) AS totalsum " +
//                    "FROM order " +
//                    "WHERE userid=?";

    private MenuOrderDaoImpl() {
    }

    private static final class MySqlMenuOrderDaoInstanceHolder {
        private static final MenuOrderDao INSTANCE = new MenuOrderDaoImpl();
    }

    /**
     * Gets instance.
     *
     * @return the instance of movie dao
     */
    public static MenuOrderDao getInstance() {
        return MenuOrderDaoImpl.MySqlMenuOrderDaoInstanceHolder.INSTANCE;
    }

    @Override
    public int add(MenuOrder menuOrder) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_MENU_ORDER, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setInt(1, menuOrder.getUserid());
            statement.setInt(2, menuOrder.getDishid());
            int rowsUpdate = statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int key = resultSet.getInt(1);
                    menuOrder.setDishid(key);
                }
            }
            return rowsUpdate;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while adding a movie", e);
            throw new DaoException("Error while adding a movie", e);
        }
    }

    @Override
    public int update(MenuOrder menuOrder) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SEND_ORDER)
        ) {
            statement.setInt(1, menuOrder.getUserid());
            return statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while updating a movie", e);
            throw new DaoException("Error while updating a movie", e);
        }
    }

    @Override
    public int delete(MenuOrder menuOrder) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE_MENU_ORDER)
        ) {
            statement.setLong(1, menuOrder.getDishid());
            return statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while updating a movie", e);
            throw new DaoException("Error while updating a movie", e);
        }
    }

    @Override
    public Optional<MenuOrder> findMenuByDishId(int dishid) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_DISH_ID)
        ) {
            statement.setLong(1, dishid);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    MenuOrder menuOrder = createOrderMenu(resultSet);
                    return Optional.of(menuOrder);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a movie", e);
            throw new DaoException("Error while selecting a movie", e);
        }
        return Optional.empty();
    }

    @Override
    public List<MenuOrder> findOrderByOrder(MenuOrder menuOrder) throws DaoException {
        List<MenuOrder> menuOrderList = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)
        ) {
            statement.setInt(1, menuOrder.getDishid());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    menuOrderList.add(createOrderMenu(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a movie", e);
            throw new DaoException("Error while selecting a movie", e);
        }
        return null;
    }

    @Override
    public Optional<MenuOrder> findMenuOrderByUserId(int userid) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_USER_ID)
        ) {
            statement.setInt(1, userid);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    MenuOrder menuOrder = createCartMenu(resultSet);
                    return Optional.of(menuOrder);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a movie", e);
            throw new DaoException("Error while selecting a movie", e);
        }
        return Optional.empty();
    }

    private MenuOrder createOrderMenu(ResultSet resultSet) throws SQLException {
        return MenuOrder.builder()
                .setUserid(resultSet.getInt(1))
                .build();
    }
    private MenuOrder createCartMenu(ResultSet resultSet) throws SQLException {
        return MenuOrder.builder()
                //.setUserid(resultSet.getInt(1))
                .setDishName(resultSet.getString(1))
                .setPrice(resultSet.getInt(2))
                .build();
    }
}

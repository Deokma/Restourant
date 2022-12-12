package by.popolamov.restourant.model.dao.impl;

import by.popolamov.restourant.exception.ConnectionPoolException;
import by.popolamov.restourant.exception.DaoException;
import by.popolamov.restourant.model.dao.MenuOrderDao;
import by.popolamov.restourant.model.entity.*;
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
    private static final String SQL_DELETE_MENU_ORDER =
            "DELETE FROM menuorder " +
                    "WHERE userid=?";
    private static final String SQL_SELECT_BY_DISH_ID =
            "SELECT dishid, dishname FROM menu WHERE dishid=?";
    private static final String SQL_SELECT_BY_USER_ID =
            "SELECT dishname, price FROM menuorder WHERE userid=?";
    private static final String SQL_SELECT_ALL =
            "SELECT dishname, price FROM menuorder";
    private static final String SQL_SEND_ORDER =
            "INSERT INTO \"order\" (orderid, userid, totalsum, status) " +
                    "SELECT max(orderid), userid, SUM(price), 2 " +
                    "FROM menuorder " +
                    "WHERE userid=? " +
                    "GROUP BY orderid ";
    private static final String SQL_SELECT_BY_QUENTITY =
            "SELECT orderid,dishid, userid, dishname, price FROM menuorder WHERE quentity=?";

    private MenuOrderDaoImpl() {
    }

    private static final class MySqlMenuOrderDaoInstanceHolder {
        private static final MenuOrderDao INSTANCE = new MenuOrderDaoImpl();
    }

    /**
     * Gets instance.
     *
     * @return the instance of menuorder dao
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
            logger.error("Error while adding a Menu Order", e);
            throw new DaoException("Error while adding a Menu Order", e);
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
            logger.error("Error while updating a Menu Order", e);
            throw new DaoException("Error while updating a Menu Order", e);
        }
    }

    @Override
    public int delete(MenuOrder menuOrder) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE_MENU_ORDER)
        ) {
            statement.setInt(1, menuOrder.getUserid());
            return statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while delete a Menu Order", e);
            throw new DaoException("Error while delete a Menu Order", e);
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
            logger.error("Error while selecting a Menu Order DishId", e);
            throw new DaoException("Error while selecting a Menu Order Dishid", e);
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
            logger.error("Error while selecting a Menu Order order", e);
            throw new DaoException("Error while selecting a Menu Order order", e);
        }
        return null;
    }
    @Override
    public List<MenuOrder> findCartByQuentity(MenuOrderQuantity menuOrderQuantity) throws DaoException {
        List<MenuOrder> menuOrders = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_QUENTITY)
        ) {
            statement.setInt(1, menuOrderQuantity.toInt());
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    menuOrders.add(createOrderQuentity(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a Menu Order Quantity", e);
            throw new DaoException("Error while selecting a Menu Order Quantity", e);
        }
        return menuOrders;
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
            logger.error("Error while selecting a Menu Order userid", e);
            throw new DaoException("Error while selecting a Menu Order userid", e);
        }
        return Optional.empty();
    }

    private MenuOrder createOrderMenu(ResultSet resultSet) throws SQLException {
        return MenuOrder.builder()
                .setUserid(resultSet.getInt(1))
                .build();
    }
    private MenuOrder createOrderQuentity(ResultSet resultSet) throws SQLException {
        return MenuOrder.builder()
                .setOrderId(resultSet.getInt(1))
                .setDishId(resultSet.getInt(2))
                .setUserid(resultSet.getInt(3))
                .setDishName(resultSet.getString(4))
                .setPrice(resultSet.getInt(5))
                .build();
    }
    private MenuOrder createCartMenu(ResultSet resultSet) throws SQLException {
        return MenuOrder.builder()
                .setDishName(resultSet.getString(1))
                .setPrice(resultSet.getInt(2))
                .build();
    }
}

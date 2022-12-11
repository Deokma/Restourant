package by.popolamov.restourant.model.dao.impl;

import by.popolamov.restourant.exception.ConnectionPoolException;
import by.popolamov.restourant.exception.DaoException;
import by.popolamov.restourant.model.dao.OrderDao;
import by.popolamov.restourant.model.entity.Order;
import by.popolamov.restourant.model.entity.OrderStatus;
import by.popolamov.restourant.model.pool.CustomConnectionPool;
import by.popolamov.restourant.util.ImageInputStreamUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LogManager.getLogger(MenuDaoImpl.class);
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();
    private static final String SQL_UPDATE_ORDER =
            "UPDATE \"order\" SET status=1 " +
                    "WHERE orderid=?";
    private static final String SQL_DELETE_ORDER =
            "DELETE FROM \"order\" " +
                    "WHERE orderid=?";
    private static final String SQL_SELECT_BY_ORDER_ID =
            "SELECT orderid, userid, totalsum, status FROM \"order\" WHERE orderid=?";
    private static final String SQL_SELECT_BY_ORDER_STATUS =
            "SELECT orderid, userid, totalsum, status FROM \"order\" WHERE status=?";

    @Override
    public int update(Order order) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER)
        ) {
            statement.setInt(1, order.getOrderid());
            return statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while updating a order", e);
            throw new DaoException("Error while updating a order", e);
        }
    }

    @Override
    public int delete(Order order) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER)
        ) {
            statement.setLong(1, order.getOrderid());
            return statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while delete a order", e);
            throw new DaoException("Error while updating a order", e);
        }
    }
    @Override
    public Optional<Order> findOrderByOrderId(int orderid) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ORDER_ID)
        ) {
            statement.setInt(1, orderid);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Order order = createOrder(resultSet);
                    return Optional.of(order);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a order by orderid", e);
            throw new DaoException("Error while selecting a order by orderid", e);
        }
        return Optional.empty();
    }
    @Override
    public List<Order> findOrderByIdOrder(int orderid) throws DaoException {
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ORDER_ID)
        ) {
            statement.setInt(1, orderid);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Order order = createOrder(resultSet);
                    return List.of(order);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a order by order", e);
            throw new DaoException("Error while selecting a order by order", e);
        }
        return null;
    }
    private static final class MySqlOrderDaoInstanceHolder {
        private static final OrderDao INSTANCE = new OrderDaoImpl();
    }
    @Override
    public List<Order> findOrderByOrderStatus(OrderStatus orderStatus) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ORDER_STATUS)
        ) {
            statement.setInt(1, orderStatus.toInt());
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orders.add(createOrder(resultSet));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error while selecting a order by order status", e);
            throw new DaoException("Error while selecting a order by order status", e);
        }
        return orders;
    }
    /**
     * Gets instance.
     *
     * @return the instance of order dao
     */
    public static OrderDao getInstance() {
        return OrderDaoImpl.MySqlOrderDaoInstanceHolder.INSTANCE;
    }
    private Order createOrder(ResultSet resultSet) throws SQLException {
        return Order.builder()
                .setOrderid(resultSet.getInt(1))
                .setUserid(resultSet.getInt(2))
                .setTotalsum(resultSet.getInt(3))
                .setStatus(resultSet.getInt(4))
                .build();
    }
}

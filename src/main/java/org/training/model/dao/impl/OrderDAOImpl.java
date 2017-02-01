package org.training.model.dao.impl;

import org.training.model.dao.OrderDAO;
import org.training.model.dao.exception.DAOException;
import org.training.model.entities.Item;
import org.training.model.entities.Order;
import org.training.model.entities.OrderItem;
import org.training.model.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nicko on 1/25/2017.
 */
public class OrderDAOImpl implements OrderDAO {

    public static final String SELECT_ONE_BY_ID = "SELECT * from order where id = ?";

    public static final String SELECT = "SELECT * from restaurant.order ";
    private static final String SELECT_OPENED = SELECT +
            "join user on restaurant.order.Client_idClient = user.idClient " +
            "where Status_IdStatus = 1";

    private static final String SELECT_CLOSED = SELECT +
            "join user on restaurant.order.Client_idClient = user.idClient " +
            "where Status_IdStatus = 2";

    private static final String SELECT_CLOSED_BY_USER = SELECT +
            "join user on restaurant.order.Client_idClient = user.idClient " +
            "where Status_IdStatus = 2 and Client_idClient = ?";

    private static final String SELECT_ORDERITEMS_BY_ID = SELECT +
            "join order_has_item on restaurant.order.idOrder = order_has_item.Order_idOrder " +
            "join item on restaurant.order_has_item.Item_idItem = item.idItem " +
            "where idOrder = ?";

    //private static final String SELECT_CLOSED_BY_USER = SELECT + "where Status_idStatus = 2 and Client_idClient = ?";

    private static final String SET_ORDER_CLOSED = "UPDATE restaurant.order set Status_IdStatus = 2 where idOrder = ?";
    private static final String SET_ORDER_PAID = "UPDATE restaurant.order set Status_IdStatus = 3 where idOrder = ?";
    private static final String CREATE_ORDER = "INSERT INTO restaurant.order (`Client_idClient`, `dateCreated`,`totalPrice`) VALUES (?,?,?)";
    private static final String ADD_ORDER_ITEM = "INSERT INTO order_has_item (`Order_idOrder`, `Item_idItem`,`amount`,`price`) VALUES (?,?,?,?)";
   /* public static final String REMOVE_ORDER_ITEM = "DELETE INTO order_has_item (`Order_idOrder`, `Item_idItem`,`amount`,`price`) VALUES (?,?,?,?)"*/

    private Connection connection;

    public OrderDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Order order) {
        Integer generatedId = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER)) {
            preparedStatement.setInt(1, order.getUser().getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(order.getDateCreated().now()));
            preparedStatement.setDouble(3, order.getTotalPrice());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            generatedId = rs.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        createOrderItems(order, generatedId);

    }

    private void createOrderItems(Order order, Integer idOrder) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER_ITEM, Statement.RETURN_GENERATED_KEYS)) {
            for (OrderItem orderItem : order.getOrderItems()) {
                preparedStatement.setInt(1, idOrder);
                preparedStatement.setInt(2, orderItem.getItem().getId());
                preparedStatement.setInt(3, orderItem.getItemAmount());
                preparedStatement.setDouble(4, orderItem.getPrice());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Order> getOpened() { //todo: remove dublicates
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_OPENED)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private Set<OrderItem> getOrderItemsByOrderId(Order order, Integer orderId) {
        Set<OrderItem> orderItems = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ORDERITEMS_BY_ID)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setPrice(resultSet.getDouble("price"));
                orderItem.setItemAmount(resultSet.getInt("amount"));
                Item item = new Item(resultSet.getInt("idItem"), resultSet.getDouble("price"),
                        resultSet.getString("title"), resultSet.getInt("weight"));
                orderItem.setItem(item);
                orderItems.add(orderItem);
            }
            return orderItems;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void setClosedById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(SET_ORDER_CLOSED)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Order> getClosed() {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CLOSED)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Order> getUserClosedOrders(Integer userId) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CLOSED_BY_USER)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void setOrderPaidById(Integer orderId) {
        try (PreparedStatement statement = connection.prepareStatement(SET_ORDER_PAID)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        Integer orderId = resultSet.getInt("idOrder");
        order.setId(orderId);
        User user = new User();
        user.setId(resultSet.getInt("idClient"));
        user.setLogin(resultSet.getString("login"));
        user.setEmail(resultSet.getString("email"));
        user.setRole(resultSet.getInt("Role_idRole"));
        order.setUser(user);
        order.setDateCreated(resultSet.getTimestamp("dateCreated").toLocalDateTime());
        order.setTotalPrice(resultSet.getDouble("totalPrice"));
        order.setOrderItems(getOrderItemsByOrderId(order, orderId));
        return order;
    }
}

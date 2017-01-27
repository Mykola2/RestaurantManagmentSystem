package org.training.model.dao.impl;

import org.training.model.dao.OrderDAO;
import org.training.model.entities.Order;
import org.training.model.entities.OrderItem;
import org.training.model.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicko on 1/25/2017.
 */
public class OrderDAOImpl implements OrderDAO {

    public static final String SELECT_ONE_BY_ID = "SELECT * from order where id = ?";
    public static final String SELECT_ALL = "SELECT * from order";
    public static final String CREATE_ORDER = "INSERT INTO restaurant.order (`Client_idClient`, `dateCreated`,`totalPrice`) VALUES (?,?,?)";
    public static final String ADD_ORDER_ITEM = "INSERT INTO order_has_item (`Order_idOrder`, `Item_idItem`,`amount`,`price`) VALUES (?,?,?,?)";
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
            preparedStatement.setDate(2, Date.valueOf(order.getDateCreated()));
            preparedStatement.setDouble(3, order.getTotalPrice());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            generatedId = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        createOrderItems(order, generatedId);

    }

    public void createOrderItems(Order order, Integer idOrder) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER_ITEM, Statement.RETURN_GENERATED_KEYS)) {
            for (OrderItem orderItem : order.getOrderItems()) {
                preparedStatement.setInt(1, idOrder);
                preparedStatement.setInt(2, orderItem.getItem().getId());
                preparedStatement.setInt(3, orderItem.getItemAmount());
                preparedStatement.setDouble(4, orderItem.getPrice());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  /*  @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL);) {
            ResultSet resultSet = statement.executeQuery();
            while ((resultSet.next())) {
                Order order= new Order();
                order.setId(resultSet.getInt(2));
                order.setUser();
                //orders.add(item);
            }
            return orders;
        } catch (Exception e) {
            throw new RuntimeException("Error by getting orders", e);
        }
    }*/

    @Override
    public Order findById(Integer id) {
        return null;
    }

    @Override
    public Order setClosed(Order order) {
        return null;
    }

    @Override
    public void addToOrder(OrderItem item) {

    }

    @Override
    public void removeFromOrder(OrderItem item) {

    }
}

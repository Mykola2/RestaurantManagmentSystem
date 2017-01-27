package org.training.model.dao;

import org.training.model.entities.Order;
import org.training.model.entities.OrderItem;
import org.training.model.entities.User;

import java.util.List;

/**
 * Created by nicko on 1/25/2017.
 */
public interface OrderDAO {

    void create(Order order);

    //List<Order> getAll();
    Order findById(Integer id);

    Order setClosed(Order order);

    void addToOrder(OrderItem item);

    void removeFromOrder(OrderItem item);

}

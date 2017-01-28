package org.training.model.dao;

import org.training.model.entities.Order;
import org.training.model.entities.OrderItem;

import java.util.List;

/**
 * Created by nicko on 1/25/2017.
 */
public interface OrderDAO {

    void create(Order order);

    List<Order> getOpened();

    Order findById(Integer id);

    void setClosedById(Integer id);

    void addToOrder(OrderItem item);

    void removeFromOrder(OrderItem item);

    List<Order> getClosed();
}

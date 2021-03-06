package org.training.dao;

import org.training.model.entities.Order;

import java.util.List;

/**
 * Created by nicko on 1/25/2017.
 */
public interface OrderDAO {

    void create(Order order);

    List<Order> getOpened();

    void setClosedById(Integer id);

    List<Order> getClosed();

    List<Order> getUserClosedOrders(Integer userId);

    void setOrderPaidById(Integer orderId);
}

package org.training.service;

import org.training.model.entities.Order;

import java.util.List;

/**
 * Created by nicko on 1/27/2017.
 */
public interface OrderService {
    void create(Order order);

    List<Order> getOpened();

    void setClosedById(Integer id);

    List<Order> getClosed();

    boolean checkBalance(Double totalPrice, Double currentBalance);

    List<Order> getUserClosedOrders(Integer userId);

    void setOrderPaidById(Integer orderId);


}

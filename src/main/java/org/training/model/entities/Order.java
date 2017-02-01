package org.training.model.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nicko on 1/24/2017.
 */
public class Order {
    private Integer id;
    private LocalDateTime dateCreated;
    private Double totalPrice;
    private User user;
    private Status status;
    private Set<OrderItem> orderItems;

    public Order() {
        orderItems = new HashSet<>();
        this.status = Status.CREATED;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        for (OrderItem item : orderItems) {
            item.setOrder(this);
        }
        this.orderItems = orderItems;
    }
}

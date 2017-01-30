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
    private Boolean isOpen;
    private Set<OrderItem> orderItems;

    public Order() {
        // default constructor
    }

    public Order(OrderItem firstItem) {
        orderItems = new HashSet<>();
        orderItems.add(firstItem);
    }

    public Order(User user, Set<OrderItem> items) {
        this.user = user;
        this.orderItems = items;
        double price = 0.0;

        for (OrderItem item : items) {
            price += item.getPrice();
            item.setOrder(this);
        }
        this.totalPrice = price;
        this.isOpen = true;
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

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen() {
        isOpen = true;
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

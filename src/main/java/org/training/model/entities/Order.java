package org.training.model.entities;

import com.sun.org.apache.xpath.internal.operations.Or;

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
        this.orderItems = new HashSet<>();
        this.status = Status.CREATED;
        this.totalPrice = 0.0;
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

    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
        totalPrice += item.getPrice();
    }

    public static class Builder {
        private Order order = new Order();

        public Builder setId(int id) {
            order.id = id;
            return this;
        }

        public Builder setDateCreated(LocalDateTime dateCreated) {
            order.dateCreated = dateCreated;
            return this;
        }

        public Builder setTotalPrice(Double totalPrice) {
            order.totalPrice = totalPrice;
            return this;
        }


        public Builder setUser(User user) {
            order.user = user;
            return this;
        }

        public Builder setStatus(Status status) {
            order.status = status;
            return this;
        }

        public Builder setOrderItems(Set<OrderItem> orderItems) {
            for (OrderItem item : orderItems) {
                item.setOrder(order);
            }
            order.orderItems = orderItems;
            return this;
        }

        public Order build() {
            return order;
        }
    }
}

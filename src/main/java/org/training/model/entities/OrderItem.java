package org.training.model.entities;

/**
 * Created by nicko on 1/24/2017.
 */
public class OrderItem {
    private Integer id;
    private Order order;
    private Item item;
    private Integer itemAmount;
    private Double price;

    public OrderItem(Item item) {
        this.item = item;
        this.itemAmount = 1;
        this.price = item.getPrice();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Integer itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderItem other = (OrderItem) obj;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        return result;
    }

    public void addItem(Item item) {
        if (this.item == null) {
            this.item = item;
            this.itemAmount = 1;
            this.price = item.getPrice();
        } else if (this.item.equals(item)) {
            this.itemAmount++;
            this.price += item.getPrice();
        } else
            throw new IllegalArgumentException("dishes not equals");
    }

    public OrderItem removeItem(Integer itemId) {
        if (this.item.getId().equals(itemId))
            if (itemAmount > 1) {
                itemAmount--;
                price -= item.getPrice();
            } else
                return null;
        return this;
    }
}

package org.training.model.entities;

/**
 * Created by nicko on 1/24/2017.
 */
public class Item {
    private Integer id;
    private Double price;
    private String name;
    private Integer weight;

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static class Builder{
        Item item = new Item();

        public Builder setId(Integer id) {
            item.id = id;
            return this;
        }

        public Builder setPrice(Double price) {
            item.price = price;
            return this;
        }

        public Builder setName(String name) {
            item.name = name;
            return this;
        }

        public Builder setWeight(Integer weight) {
            item.weight = weight;
            return this;
        }

        public Item build() {
            return item;
        }
    }
}

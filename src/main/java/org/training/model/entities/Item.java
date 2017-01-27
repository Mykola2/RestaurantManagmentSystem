package org.training.model.entities;

/**
 * Created by nicko on 1/24/2017.
 */
public class Item {
    private Integer id;
    private Double price;
    private String name;
    private String weight;

    public Item(Integer id, Double price, String name, String weight) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
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
}

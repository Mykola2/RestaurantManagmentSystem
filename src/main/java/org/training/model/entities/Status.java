package org.training.model.entities;

/**
 * Created by nicko on 1/31/2017.
 */
public enum Status {
    CREATED("Created"),
    CLOSED("Closed"),
    PAID("Paid");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

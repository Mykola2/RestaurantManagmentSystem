package org.training.model.entities;

/**
 * Created by nicko on 1/25/2017.
 */
public enum Role {
    USER("User"),
    ADMIN("Admin");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

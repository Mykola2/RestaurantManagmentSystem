package org.training.model.entities;

/**
 * Created by nicko on 1/25/2017.
 */
public enum Role {
    ADMIN("Admin"),
    USER("User");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

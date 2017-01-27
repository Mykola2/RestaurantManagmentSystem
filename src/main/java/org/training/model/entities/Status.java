package org.training.model.entities;

/**
 * Created by nicko on 1/24/2017.
 */
public enum Status {
    NEW("New"),
    CREATED("Created"),
    DONE("Done");
    private final String name;

    Status(String s) {
        name = s;
    }
}

package org.training.dao;

import org.training.model.entities.Item;

import java.util.List;

/**
 * Created by nicko on 1/25/2017.
 */
public interface ItemDAO {
    List<Item> getAll();

    Item findById(Integer id);

    void create(Item item);
}

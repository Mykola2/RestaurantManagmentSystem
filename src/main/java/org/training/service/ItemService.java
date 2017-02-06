package org.training.service;

import org.training.model.entities.Item;

import java.util.List;

/**
 * Created by nicko on 1/26/2017.
 */
public interface ItemService {
    List<Item> getAll();

    Item findById(Integer id);

    Item create(Item item);
}

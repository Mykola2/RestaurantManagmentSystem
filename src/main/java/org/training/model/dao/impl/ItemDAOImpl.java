package org.training.model.dao.impl;

import org.training.model.dao.ItemDAO;
import org.training.model.entities.Item;
import org.training.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicko on 1/25/2017.
 */
public class ItemDAOImpl implements ItemDAO {
    private Connection connection;
    public static final String SELECT_ONE_BY_ID = "SELECT * from item where idItem = ?";
    public static final String SELECT_ALL = "SELECT * from item";

    public ItemDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while ((resultSet.next())) {
                Item item = new Item(resultSet.getInt(1), resultSet.getDouble(3),
                        resultSet.getString(2), resultSet.getString(4));
                items.add(item);
            }
            return items;
        } catch (Exception e) {
            throw new RuntimeException("Error by getting items", e);
        }
    }

    @Override
    public Item findById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ONE_BY_ID)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Item user = new Item(resultSet.getInt(1), resultSet.getDouble(3),
                    resultSet.getString(2), resultSet.getString(4));
            return user;
        } catch (Exception e) {
            throw new RuntimeException("Error by finding Item", e);
        }
    }
}

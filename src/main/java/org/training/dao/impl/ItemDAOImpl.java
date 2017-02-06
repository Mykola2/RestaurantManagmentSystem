package org.training.dao.impl;

import org.apache.log4j.Logger;
import org.training.dao.ItemDAO;
import org.training.dao.exception.DAOException;
import org.training.model.entities.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicko on 1/25/2017.
 */
public class ItemDAOImpl implements ItemDAO {

    private static final Logger logger = Logger.getLogger(ItemDAO.class);

    private Connection connection;
    private static final String SELECT_ONE_BY_ID = "SELECT * from item where idItem = ?";
    private static final String SELECT_ALL = "SELECT * from item";
    public static final String CREATE_ITEM = "INSERT INTO item (`title`, `price`, `weight`) VALUES (?,?,?)";

    public ItemDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item.Builder()
                        .setId(resultSet.getInt(1))
                        .setPrice(resultSet.getDouble(3))
                        .setName(resultSet.getString(2))
                        .setWeight(resultSet.getInt(4))
                        .build();
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            logger.error("Error retrieving all items", e);
            throw new DAOException(e);
        }
    }

    @Override
    public Item findById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ONE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new Item.Builder()
                    .setId(resultSet.getInt(1))
                    .setPrice(resultSet.getDouble(3))
                    .setName(resultSet.getString(2))
                    .setWeight(resultSet.getInt(4))
                    .build();
        } catch (SQLException e) {
            logger.error("Error retrieving item by id", e);
            throw new DAOException(e);
        }
    }

    @Override
    public void create(Item item) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ITEM)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setInt(3, item.getWeight());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error creating item", e);
            throw new DAOException(e);
        }
    }
}

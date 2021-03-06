package org.training.service.impl;

import org.training.dao.factory.DAOFactory;
import org.training.dao.factory.DAOFactoryImpl;
import org.training.dao.ItemDAO;
import org.training.dao.connection.AbstractConnection;
import org.training.dao.connection.ConnectionManager;
import org.training.model.entities.Item;
import org.training.service.ItemService;
import org.training.service.exception.ServiceException;

import java.util.List;

/**
 * Created by nicko on 1/26/2017.
 */
public class ItemServiceImpl implements ItemService {

    private ConnectionManager connectionManager;
    private DAOFactory daoFactory;

    private ItemServiceImpl(){

    }

    private ItemServiceImpl(ConnectionManager connectionManager, DAOFactory daoFactory) {
        this.connectionManager = connectionManager;
        this.daoFactory = daoFactory;
    }

    private static class InstanceHolder {
        private static final ItemServiceImpl INSTANCE = new ItemServiceImpl(ConnectionManager.getInstance(), DAOFactoryImpl.getInstance());
    }

    public static ItemServiceImpl getInstance() {
        return ItemServiceImpl.InstanceHolder.INSTANCE;
    }

    @Override
    public List<Item> getAll() {
        try (AbstractConnection connection = connectionManager.getMySQLConnection()) {
            ItemDAO itemDAO = daoFactory.getItemDAO(connection);
            return itemDAO.getAll();
        }


    }

    @Override
    public Item findById(Integer id) {

        try (AbstractConnection connection = connectionManager.getMySQLConnection()) {
            ItemDAO itemDAO = daoFactory.getItemDAO(connection);
            return itemDAO.findById(id);
        }
    }

    @Override
    public Item create(Item item) {
            try (AbstractConnection connection = connectionManager.getMySQLConnection()) {
                ItemDAO itemDAO = daoFactory.getItemDAO(connection);
                connection.beginTransaction();
                itemDAO.create(item);
                connection.commit();
            } catch (ServiceException e) {
                throw new ServiceException("Error on create item", e);
            }
        return item;
        }
}

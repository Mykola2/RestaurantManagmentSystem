package org.training.service.impl;

import org.training.model.dao.factory.DAOFactory;
import org.training.model.dao.factory.DAOFactoryImpl;
import org.training.model.dao.ItemDAO;
import org.training.model.dao.connection.AbstractConnection;
import org.training.model.dao.connection.ConnectionManager;
import org.training.model.entities.Item;
import org.training.service.ItemService;

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
}

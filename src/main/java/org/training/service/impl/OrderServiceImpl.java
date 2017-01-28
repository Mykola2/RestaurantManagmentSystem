package org.training.service.impl;

import org.training.model.dao.factory.DAOFactory;
import org.training.model.dao.factory.DAOFactoryImpl;
import org.training.model.dao.OrderDAO;
import org.training.model.dao.connection.AbstractConnection;
import org.training.model.dao.connection.ConnectionManager;
import org.training.model.entities.Order;
import org.training.service.OrderService;

import java.util.List;

/**
 * Created by nicko on 1/27/2017.
 */
public class OrderServiceImpl implements OrderService {
    private ConnectionManager connectionManager;
    private DAOFactory daoFactory;

    private OrderServiceImpl(ConnectionManager connectionManager, DAOFactory daoFactory) {
        this.connectionManager = connectionManager;
        this.daoFactory = daoFactory;
    }

    private static class InstanceHolder {
        private static final OrderServiceImpl INSTANCE = new OrderServiceImpl(ConnectionManager.getInstance(), DAOFactoryImpl.getInstance());
    }

    public static OrderServiceImpl getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public void create(Order order) {
        try (AbstractConnection connection = connectionManager.getMySQLConnection()) {
            OrderDAO orderDAO = daoFactory.getOrderDAO(connection);
            if (order != null) {
                connection.beginTransaction();
                orderDAO.create(order);
                connection.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getOpened() {
        try (AbstractConnection connection = connectionManager.getMySQLConnection()) {
            OrderDAO orderDAO = daoFactory.getOrderDAO(connection);
            return orderDAO.getOpened();
        }
    }

    @Override
    public void setClosedById(Integer id) {
        try (AbstractConnection connection = connectionManager.getMySQLConnection()) {
            OrderDAO orderDAO = daoFactory.getOrderDAO(connection);
            connection.beginTransaction();
            orderDAO.setClosedById(id);
            connection.commit();

        }
    }

    @Override
    public List<Order> getClosed() {
        try (AbstractConnection connection = connectionManager.getMySQLConnection()) {
            OrderDAO orderDAO = daoFactory.getOrderDAO(connection);
            return orderDAO.getClosed();
        }
    }
}

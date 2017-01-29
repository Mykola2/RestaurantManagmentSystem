package org.training.service.impl;

import org.training.model.dao.factory.DAOFactory;
import org.training.model.dao.factory.DAOFactoryImpl;
import org.training.model.dao.UserDAO;
import org.training.model.dao.connection.AbstractConnection;
import org.training.model.dao.connection.ConnectionManager;
import org.training.model.entities.User;
import org.training.service.UserService;

/**
 * Created by nicko on 1/25/2017.
 */
public class UserServiceImpl implements UserService {
    private ConnectionManager connectionManager;
    private DAOFactory daoFactory;

    private UserServiceImpl(ConnectionManager connectionManager, DAOFactory daoFactory) {
        this.connectionManager = connectionManager;
        this.daoFactory = daoFactory;
    }

    private static class InstanceHolder {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl(ConnectionManager.getInstance(), DAOFactoryImpl.getInstance());
    }

    public static UserServiceImpl getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public void create(User user) {
        try (AbstractConnection connection = connectionManager.getMySQLConnection()) {
            UserDAO userDao = daoFactory.getUserDAO(connection);
            if (user != null) {
                connection.beginTransaction();
                userDao.create(user);
                connection.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User find(Integer id) {
        try (AbstractConnection connection = connectionManager.getMySQLConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            return userDAO.find(id);
        }
    }

    @Override
    public User findByLogin(String login) {
        try (AbstractConnection connection = connectionManager.getMySQLConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            return userDAO.findByLogin(login);
        }
    }

    @Override
    public void withdraw(Double totalprice, Integer userId) {
        try (AbstractConnection connection = connectionManager.getMySQLConnection()) {
            UserDAO userDao = daoFactory.getUserDAO(connection);
            connection.beginTransaction();
            userDao.withdraw(totalprice,userId);
            connection.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

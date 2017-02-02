package org.training.service.impl;

import org.training.dao.factory.DAOFactory;
import org.training.dao.factory.DAOFactoryImpl;
import org.training.dao.UserDAO;
import org.training.dao.connection.AbstractConnection;
import org.training.dao.connection.ConnectionManager;
import org.training.model.entities.User;
import org.training.service.UserService;
import org.training.service.exception.ServiceException;

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
    public User create(User user) {
        if (!isUserExist(user)) {
            try (AbstractConnection connection = connectionManager.getMySQLConnection()) {
                UserDAO userDao = daoFactory.getUserDAO(connection);
                connection.beginTransaction();
                userDao.create(user);
                connection.commit();
            } catch (ServiceException e) {
                throw new ServiceException("Error on withdraw", e);
            }
            return user;
        }else
            return null;

    }

    private Boolean isUserExist(User user) {
        Boolean isExist = false;
        try (AbstractConnection connection = connectionManager.getMySQLConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            if ((userDAO.findByLogin(user.getLogin()) != null) || userDAO.findByEmail(user.getEmail()) != null) {
                isExist = true;
            }
        } catch (ServiceException e) {
            throw new ServiceException("Failed to check if the user exists", e);
        }
        return isExist;
    }

    @Override
    public User find(Integer id) {
        try (AbstractConnection connection = connectionManager.getMySQLConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            return userDAO.find(id);
        }
    }

    @Override
    public User login(String login, String password) {
        User existingUser;
        try (AbstractConnection connection = connectionManager.getMySQLConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            existingUser = userDAO.findByLogin(login);
        } catch (ServiceException e) {
            throw new ServiceException("Login Failed", e);
        }
        if (existingUser != null) {
            if (existingUser.getPassword().equals(password)) {
                return existingUser;
            }
        }
        return null;
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
            userDao.withdraw(totalprice, userId);
            connection.commit();
        } catch (ServiceException e) {
            throw new ServiceException("Error on withdraw", e);
        }
    }
}

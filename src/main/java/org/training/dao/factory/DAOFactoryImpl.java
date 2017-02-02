package org.training.dao.factory;

import org.training.dao.ItemDAO;
import org.training.dao.OrderDAO;
import org.training.dao.UserDAO;
import org.training.dao.connection.AbstractConnection;
import org.training.dao.exception.DAOException;
import org.training.dao.impl.ItemDAOImpl;
import org.training.dao.impl.OrderDAOImpl;
import org.training.dao.impl.UserDAOImpl;

/**
 * Created by nicko on 1/25/2017.
 */
public class DAOFactoryImpl implements DAOFactory {

    private DAOFactoryImpl() {
    }

    private static class InstanceHolder {
        private static final DAOFactoryImpl INSTANCE = new DAOFactoryImpl();
    }

    public static DAOFactoryImpl getInstance() {
        return InstanceHolder.INSTANCE;
    }


    @Override
    public OrderDAO getOrderDAO(AbstractConnection connection) {
        if (connection.getConnection() != null) {
            return new OrderDAOImpl(connection.getConnection());
        } else {
            throw new DAOException();
        }
    }

    @Override
    public ItemDAO getItemDAO(AbstractConnection connection) {
        if (connection.getConnection() != null) {
            return new ItemDAOImpl(connection.getConnection());
        } else {
            throw new DAOException();
        }
    }

    @Override
    public UserDAO getUserDAO(AbstractConnection connection) {
        if (connection.getConnection() != null) {
            return new UserDAOImpl(connection.getConnection());
        } else {
            throw new DAOException();
        }
    }
}

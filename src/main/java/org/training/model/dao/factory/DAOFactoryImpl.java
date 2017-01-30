package org.training.model.dao.factory;

import org.training.model.dao.ItemDAO;
import org.training.model.dao.OrderDAO;
import org.training.model.dao.UserDAO;
import org.training.model.dao.connection.AbstractConnection;
import org.training.model.dao.exception.DAOException;
import org.training.model.dao.impl.ItemDAOImpl;
import org.training.model.dao.impl.OrderDAOImpl;
import org.training.model.dao.impl.UserDAOImpl;

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

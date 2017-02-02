package org.training.dao.factory;

import org.training.dao.ItemDAO;
import org.training.dao.OrderDAO;
import org.training.dao.UserDAO;
import org.training.dao.connection.AbstractConnection;

/**
 * Created by nicko on 1/25/2017.
 */
public interface DAOFactory {
    OrderDAO getOrderDAO(AbstractConnection connection);

    ItemDAO getItemDAO(AbstractConnection connection);

    UserDAO getUserDAO(AbstractConnection connection);
}

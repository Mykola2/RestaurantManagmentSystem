package org.training.model.dao.factory;

import org.training.model.dao.ItemDAO;
import org.training.model.dao.OrderDAO;
import org.training.model.dao.UserDAO;
import org.training.model.dao.connection.AbstractConnection;
import org.training.model.entities.Item;
import org.training.model.entities.Order;
import org.training.model.entities.User;

/**
 * Created by nicko on 1/25/2017.
 */
public interface DAOFactory {
    OrderDAO getOrderDAO(AbstractConnection connection);

    ItemDAO getItemDAO(AbstractConnection connection);

    UserDAO getUserDAO(AbstractConnection connection);
}

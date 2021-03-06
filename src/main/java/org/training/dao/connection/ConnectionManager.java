package org.training.dao.connection;

import org.training.dao.exception.DAOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by nicko on 1/25/2017.
 */
public class ConnectionManager {
    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final ConnectionManager INSTANCE = new ConnectionManager();
    }

    public AbstractConnection getMySQLConnection() {
        InitialContext context = null;
        try {
            context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/RestDB");
            return new MySQLConnection(dataSource.getConnection());
        } catch (SQLException e) {
            throw new DAOException();
        } catch (NamingException e) {
            throw new RuntimeException(); // todo
        }

    }
}

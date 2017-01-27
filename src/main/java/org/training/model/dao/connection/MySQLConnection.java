package org.training.model.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by nicko on 1/25/2017.
 */
public class MySQLConnection implements AbstractConnection {
    private Connection connection;
    private boolean isTransactionCommitted = false;
    private boolean isTransactionBegin = false;

    public MySQLConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void beginTransaction() {
        try {
            isTransactionBegin = true;
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void rollback() {
        try {
            isTransactionCommitted = true;
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
            isTransactionCommitted = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            if (isTransactionBegin && !isTransactionCommitted) {
                rollback();
            }
            connection.close();
        } catch (SQLException e) {

        }
    }
}

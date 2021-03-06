package org.training.dao.connection;

import java.sql.Connection;

/**
 * Created by nicko on 1/25/2017.
 */
public interface AbstractConnection extends AutoCloseable {

    Connection getConnection();

    void beginTransaction();

    void rollback();

    void commit();

    @Override
    void close();
}

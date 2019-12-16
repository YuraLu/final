package by.epam.lukashevich.dao.core.pool.connection;

import by.epam.lukashevich.dao.core.pool.ConnectionPool;
import by.epam.lukashevich.dao.core.pool.impl.DatabaseConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Represents ProxyConnection class which give us possibility
 * to use connections in try-with-resources.
 *
 * @author Yuri Lukashevich
 * @version 1.0
 * @since JDK1.0
 */
public class ProxyConnection implements AutoCloseable {

    private static final Logger logger = LogManager.getLogger(ProxyConnection.class);

    private final ConnectionWrapper connectionWrapper;
    private final ConnectionPool pool = DatabaseConnectionPool.getInstance();

    public ProxyConnection(Connection connection) {
        this.connectionWrapper = new ConnectionWrapper(connection);
    }

    @Override
    public void close() {
        pool.putBackConnection(this);
    }

    public ConnectionWrapper getConnectionWrapper() {
        return connectionWrapper;
    }

    public void destroy() {
        try {
            this.connectionWrapper.realClose();
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
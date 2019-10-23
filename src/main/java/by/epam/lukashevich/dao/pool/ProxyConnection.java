package by.epam.lukashevich.dao.pool;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ProxyConnection implements AutoCloseable {
    private static final Logger logger = LogManager.getLogger(ProxyConnection.class);

    private Connection connection;

    private DatabaseConnectionPool pool = DatabaseConnectionPool.getInstance();

    public ProxyConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        pool.putBackConnection(this);
    }


    public void destroy() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            logger.error(ex);
        }
    }
}

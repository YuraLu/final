package by.epam.lukashevich.dao.pool;


import by.epam.lukashevich.dao.pool.exception.CannotGetJdbcConnectionException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DatabaseConnectionPool {
    private static final Logger logger = LogManager.getLogger(DatabaseConnectionPool.class);

    private static DatabaseConnectionPool instance = new DatabaseConnectionPool();

    public static DatabaseConnectionPool getInstance() {
        return instance;
    }

    private DatabaseConnectionPool() {
    }

    /************************************************************************/

    private BlockingQueue<ProxyConnection> availableConnections;
    private BlockingQueue<ProxyConnection> usedConnections;
    private static final int INITIAL_POOL_SIZE = 15;

    public void init(final String driver, final String url, final String user, final String password) {
        try {
            Class.forName(driver);

            availableConnections = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
            usedConnections = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);

            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                Connection connection = createConnection(url, user, password);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                availableConnections.add(proxyConnection);
            }

        } catch (ClassNotFoundException | SQLException e) {
            // logger.fatal(e);
            // throw new CannotGetJdbcConnectionException(e);
        }
    }


    private static Connection createConnection(final String url,
                                               final String user,
                                               final String password)
            throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public ProxyConnection getConnection() {
        return doGetConnection();
    }

    private ProxyConnection doGetConnection() {
        // logger.info("\nPOOL FREE SIZE = " + availableConnections.size());
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = availableConnections.take();
            usedConnections.add(proxyConnection);
        } catch (InterruptedException e) {
            logger.error(e);
            throw new CannotGetJdbcConnectionException(e);
        }
        return proxyConnection;
    }

    public void putBackConnection(ProxyConnection connection) {
        availableConnections.add(connection);
        usedConnections.remove(connection);
    }

    public void destroyPool() {
        usedConnections.forEach(ProxyConnection::destroy);
        availableConnections.forEach(ProxyConnection::destroy);
    }
}




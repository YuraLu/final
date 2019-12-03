package by.epam.lukashevich.dao.pool.impl;


import by.epam.lukashevich.dao.pool.ConnectionPool;
import by.epam.lukashevich.dao.pool.connection.ProxyConnection;
import by.epam.lukashevich.dao.pool.exception.CannotGetJdbcConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DatabaseConnectionPool implements ConnectionPool {

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
    private static final int DEFAULT_POOL_SIZE = 16;

    public void init(final String driver, final String url, final String user, final String password, String poolSize) {
        try {
            Class.forName(driver);

            int initPoolSize = getInitPoolSize(poolSize);

            availableConnections = new ArrayBlockingQueue<>(initPoolSize);
            usedConnections = new ArrayBlockingQueue<>(initPoolSize);

            for (int i = 0; i < initPoolSize; i++) {
                Connection connection = createConnection(url, user, password);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                availableConnections.add(proxyConnection);
            }

        } catch (ClassNotFoundException | SQLException e) {
            logger.fatal(e);
            throw new CannotGetJdbcConnectionException(e);
        }
    }

    private int getInitPoolSize(String poolSize){
        try {
            return Integer.parseInt(poolSize);
        } catch (NumberFormatException e) {
            logger.error("Can't convert pool size to int, will use default value", e);
            return DEFAULT_POOL_SIZE;
        }
    }

    private static Connection createConnection(final String url,
                                               final String user,
                                               final String password)
                                               throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public ProxyConnection getConnection() {
        return doGetConnection();
    }

    private ProxyConnection doGetConnection() {
        logger.info("\nPOOL FREE SIZE = " + availableConnections.size());
        ProxyConnection proxyConnection;
        try {
            proxyConnection = availableConnections.take();
            usedConnections.add(proxyConnection);
        } catch (InterruptedException ex) {
            logger.error(ex);
            throw new CannotGetJdbcConnectionException(ex);
        }
        return proxyConnection;
    }

    @Override
    public void putBackConnection(ProxyConnection connection) {
        availableConnections.add(connection);
        usedConnections.remove(connection);
    }

    @Override
    public void destroyPool() {
        usedConnections.forEach(ProxyConnection::destroy);
        availableConnections.forEach(ProxyConnection::destroy);
    }
}


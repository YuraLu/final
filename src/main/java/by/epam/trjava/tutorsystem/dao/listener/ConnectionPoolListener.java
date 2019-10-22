package by.epam.trjava.tutorsystem.dao.listener;

import by.epam.trjava.tutorsystem.dao.pool.ConnectionPool;
import by.epam.trjava.tutorsystem.dao.pool.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConnectionPoolListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(ConnectionPoolListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        ConnectionPool.getInstance().dispose();
        LOGGER.info("Connection pool destroyed successfully");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            ConnectionPool.getInstance().initPoolData();
            LOGGER.info("Connection pool initialized successfully");
        } catch (ConnectionPoolException e) {
            LOGGER.error("ConnectionPoolException during initializing", e);
            throw new ExceptionInInitializerError("Could not initialize pool!");
        }
    }
}

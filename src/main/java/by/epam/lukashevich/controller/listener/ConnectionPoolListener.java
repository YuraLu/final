package by.epam.lukashevich.controller.listener;

import by.epam.lukashevich.dao.pool.DatabaseConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConnectionPoolListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(ConnectionPoolListener.class);

        private static final String DRIVER = "db.driver";
        private static final String URL = "db.url";
        private static final String USER = "db.user";
        private static final String PASSWORD = "db.password";

        @Override
        public void contextInitialized(ServletContextEvent servletContextEvent) {
            final ServletContext servletContext = servletContextEvent.getServletContext();
            final String driver = servletContext.getInitParameter(DRIVER);
            final String url = servletContext.getInitParameter(URL);
            final String user = servletContext.getInitParameter(USER);
            final String password = servletContext.getInitParameter(PASSWORD);
            //
            final DatabaseConnectionPool pool = DatabaseConnectionPool.getInstance();
            pool.init(driver, url, user, password);
        }

        @Override
        public void contextDestroyed(ServletContextEvent servletContextEvent) {
            DatabaseConnectionPool.getInstance().destroyPool();
        }
}

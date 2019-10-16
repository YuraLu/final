package by.epam.trjava.tutorsystem.dao.pool;

import by.epam.trjava.tutorsystem.dao.pool.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class ConnectionPool {

    private static final ConnectionPool instance = new ConnectionPool();

    private final List<Connection> connectionPool = new ArrayList<>();
    private final List<Connection> usedConnections = new ArrayList<>();
    private static int poolSize;
    private static String url;
    private static String password;
    private static String user;
    private static final int DEFAULT_POOL_SIZE = 5;
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    static {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        String driverName = dbResourceManager.getProperty(DBParameter.DB_DRIVER);
        url = dbResourceManager.getProperty(DBParameter.DB_URL);
        password = dbResourceManager.getProperty(DBParameter.DB_PASSWORD);
        user = dbResourceManager.getProperty(DBParameter.DB_USER);

        try {
            poolSize = Integer.parseInt(dbResourceManager.getProperty(DBParameter.DB_POOL_SIZE));
        } catch (NumberFormatException e) {
            LOGGER.error("Can't parse pool size. It will be 5 by default.");
            poolSize = DEFAULT_POOL_SIZE;
            try {
                LOGGER.error("Can't parse pool size. It will be 5 by default.");
                throw new ConnectionPoolException("Can't parse pool size. It will be 5 by default.", e);
            } catch (ConnectionPoolException ex) {
                ex.printStackTrace();
            }
        }

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Can't find driver.");
            try {
                throw new ConnectionPoolException("Can't find driver.", e);
            } catch (ConnectionPoolException ex) {
                ex.printStackTrace();
            }
        }
    }

    protected ConnectionPool() {
        for (int i = 0; i < poolSize; i++) {
            connectionPool.add(createConnection());
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() {
        if (connectionPool.isEmpty()) {
            connectionPool.add(createConnection());
        }

        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    private static Connection createConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            LOGGER.error("SQLException in connection pool", e);
        }
        return con;
    }


//    private BlockingQueue<Connection> connectionQueue;
//    private BlockingQueue<Connection> givenAwayConQueue;
//
//    private int poolsize;
//    private final String driverName;
//    private final String url;
//    private final String password;
//    private final String user;
//
//    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
//
//
//    private ConnectionPool() throws ConnectionPoolException {
//        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
//        this.driverName = dbResourceManager.getValue(DBParametr.DB_DRIVER);
//        this.url = dbResourceManager.getValue(DBParametr.DB_URL);
//        this.password = dbResourceManager.getValue(DBParametr.DB_PASSWORD);
//        this.user = dbResourceManager.getValue(DBParametr.DB_USER);
//
//        try {
//            this.poolsize = Integer.parseInt(dbResourceManager.getValue(DBParametr.DB_POOL_SIZE));
//        } catch (NumberFormatException e) {
//            LOGGER.error("Can't parse pool size. It will be 5 by default.");
//            poolsize = 5;
//            throw new ConnectionPoolException("Can't parse pool size. It will be 5 by default.", e);
//        }
//        initPoolData();
//    }
//
//    public void initPoolData() throws ConnectionPoolException {
//        try {
//            Class.forName(driverName);
//            connectionQueue = new ArrayBlockingQueue<Connection>(poolsize);
//            givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolsize);
//
//            for (int i = 0; i < poolsize; i++) {
//                Connection connection = DriverManager.getConnection(url, user, password);
//                //gfnthy ltrjhfnjh b flfgnth
//                connectionQueue.add(connection);
//            }
//        } catch (ClassNotFoundException e) {
//            LOGGER.error("Can't find DB class driver");
//            throw new ConnectionPoolException("Can't find DB class driver", e);
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in connection pool");
//            throw new ConnectionPoolException("SQLException in connection pool", e);
//        }
//    }


    public void closeConnection(PreparedStatement st, Connection con) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            LOGGER.warn("can't close st", e);
        }

        releaseConnection(con);
    }

    public void closeConnection(ResultSet rs, PreparedStatement st, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOGGER.warn("can't close rs", e);
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            LOGGER.warn("can't close st", e);
        }

        releaseConnection(con);
    }

    public void closeConnection(ResultSet rs, PreparedStatement st1, PreparedStatement st2, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOGGER.warn("can't close rs", e);
        }
        try {
            if (st1 != null) {
                st1.close();
            }
        } catch (SQLException e) {
            LOGGER.warn("can't close st1", e);
        }
        try {
            if (st2 != null) {
                st2.close();
            }
        } catch (SQLException e) {
            LOGGER.warn("can't close st2", e);
        }

        releaseConnection(con);
    }

    public void closeConnection(ResultSet rs, PreparedStatement st1,
                                PreparedStatement st2, PreparedStatement st3,
                                Connection con) {
        closeConnection(rs, st1, st2, con);
        try {
            if (st3 != null) {
                st3.close();
            }
        } catch (SQLException e) {
            LOGGER.warn("can't close st3", e);
        }
        releaseConnection(con);
    }

}

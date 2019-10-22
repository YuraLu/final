package by.epam.trjava.tutorsystem.dao.pool;

import by.epam.trjava.tutorsystem.dao.pool.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public final class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static final int DEFAULT_POOL_SIZE = 5;

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;
    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private static final ConnectionPool instance = new ConnectionPool();

    private ConnectionPool() {

        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getProperty(DBParameter.DB_DRIVER);
        this.url = dbResourceManager.getProperty(DBParameter.DB_URL);
        this.password = dbResourceManager.getProperty(DBParameter.DB_PASSWORD);
        this.user = dbResourceManager.getProperty(DBParameter.DB_USER);

        try {
            this.poolSize = Integer.parseInt(dbResourceManager.getProperty(DBParameter.DB_POOL_SIZE));
        } catch (NumberFormatException e) {
            LOGGER.error("NumberFormatException in constructor of ConnectionPool");
            poolSize = DEFAULT_POOL_SIZE;
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void initPoolData() throws ConnectionPoolException {

        try {
            Class.forName(driverName);
            givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);
            connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                ConnectionPool.PooledConnection pooledConnection = new ConnectionPool.PooledConnection(connection);
                connectionQueue.add(pooledConnection);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in ConnectionPool", e);
            throw new ConnectionPoolException("SQLException in ConnectionPool", e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFoundException in ConnectionPool ", e);
            throw new ConnectionPoolException("Can't find database driver class", e);
        }
    }

    public void dispose() {
        clearConnectionQueue();
    }

    private void clearConnectionQueue() {
        try {
            closeConnectionsQueue(givenAwayConQueue);
            closeConnectionsQueue(connectionQueue);
        } catch (SQLException e) {
            LOGGER.error("SQLException in ConnectionPool/clearConnectionQueue, ", e);
        }
    }

    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
            givenAwayConQueue.add(connection);

        } catch (InterruptedException e) {
            LOGGER.error("Error connecting to the data source.", e);
            throw new ConnectionPoolException("Error connecting to the data source.", e);
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        connectionQueue.offer(connection);
        return givenAwayConQueue.remove(connection);
    }


    public void closeConnection(ResultSet rs, Statement st1, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOGGER.error("ResultSet isn't closed.");
        }
        try {
            if (st1 != null) {
                st1.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Statement1 isn't closed.");
        }
        try {
            con.close();
        } catch (SQLException e) {
            LOGGER.error("Connection isn't return to the pool.");
        }
    }

    public void closeConnection(ResultSet rs, Statement st1, Statement st2, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOGGER.error("ResultSet isn't closed.");
        }
        try {
            if (st1 != null) {
                st1.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Statement1 isn't closed.");
        }
        try {
            if (st2 != null) {
                st2.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Statement2 isn't closed.");
        }
        try {
            con.close();
        } catch (SQLException e) {
            LOGGER.error("Connection isn't return to the pool.");
        }
    }

    public void closeConnection(Statement st, Connection con) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Statement1 isn't closed.");
        }
        try {
            con.close();
        } catch (SQLException e) {
            LOGGER.error("Connection isn't return to the pool.");
        }
    }

    private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection;
        while ((connection = queue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            ((ConnectionPool.PooledConnection) connection).reallyClose();
        }
    }

    private class PooledConnection implements Connection {
        private Connection connection;

        public PooledConnection(Connection c) throws SQLException {
            this.connection = c;
            this.connection.setAutoCommit(true);
        }

        public void reallyClose() throws SQLException {
            connection.close();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public void close() throws SQLException {
            if (connection.isClosed()) {
                LOGGER.error("Attempting to close closed connection.");
                throw new SQLException("Attempting to close closed connection.");
            }
            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }
            if (!givenAwayConQueue.remove(this)) {
                LOGGER.error("Error deleting connection from the given away connections pool.");
                throw new SQLException("Error deleting connection from the given away connections pool.");
            }
            if (!connectionQueue.offer(this)) {
                LOGGER.error("Error allocating connection in the pool.");
                throw new SQLException("Error allocating connection in the pool.");
            }
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
                                             int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
                                                  int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public void abort(Executor arg0) throws SQLException {
            connection.abort(arg0);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void releaseSavepoint(Savepoint arg0) throws SQLException {
            connection.releaseSavepoint(arg0);
        }

        @Override
        public void rollback(Savepoint arg0) throws SQLException {
            connection.rollback(arg0);
        }

        @Override
        public void setClientInfo(Properties arg0) throws SQLClientInfoException {
            connection.setClientInfo(arg0);
        }

        @Override
        public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
            connection.setNetworkTimeout(arg0, arg1);
        }

        @Override
        public void setSchema(String arg0) throws SQLException {
            connection.setSchema(arg0);
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
            connection.setTypeMap(arg0);
        }
    }






































//    private static final ConnectionPool instance = new ConnectionPool();
//
//    private final List<Connection> connectionPool = new ArrayList<>();
//    private final List<Connection> usedConnections = new ArrayList<>();
//    private static int poolSize;
//    private static String url;
//    private static String password;
//    private static String user;
//    private static final int DEFAULT_POOL_SIZE = 5;
//    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
//
//    static {
//        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
//        String driverName = dbResourceManager.getProperty(DBParameter.DB_DRIVER);
//        url = dbResourceManager.getProperty(DBParameter.DB_URL);
//        password = dbResourceManager.getProperty(DBParameter.DB_PASSWORD);
//        user = dbResourceManager.getProperty(DBParameter.DB_USER);
//
//        try {
//            poolSize = Integer.parseInt(dbResourceManager.getProperty(DBParameter.DB_POOL_SIZE));
//        } catch (NumberFormatException e) {
//            LOGGER.error("Can't parse pool size. It will be 5 by default.");
//            poolSize = DEFAULT_POOL_SIZE;
//            try {
//                LOGGER.error("Can't parse pool size. It will be 5 by default.");
//                throw new ConnectionPoolException("Can't parse pool size. It will be 5 by default.", e);
//            } catch (ConnectionPoolException ex) {
//                ex.printStackTrace();
//            }
//        }
//
//        try {
//            Class.forName(driverName);
//        } catch (ClassNotFoundException e) {
//            LOGGER.error("Can't find driver.");
//            try {
//                throw new ConnectionPoolException("Can't find driver.", e);
//            } catch (ConnectionPoolException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//
//    protected ConnectionPool() {
//        for (int i = 0; i < poolSize; i++) {
//            connectionPool.add(createConnection());
//        }
//    }
//
//    public static ConnectionPool getInstance() {
//        return instance;
//    }
//
//    public Connection getConnection() {
//        if (connectionPool.isEmpty()) {
//            connectionPool.add(createConnection());
//        }
//
//        Connection connection = connectionPool.remove(connectionPool.size() - 1);
//        usedConnections.add(connection);
//        return connection;
//    }
//
//    public boolean releaseConnection(Connection connection) {
//        connectionPool.add(connection);
//        return usedConnections.remove(connection);
//    }
//
//    public int getSize() {
//        return connectionPool.size() + usedConnections.size();
//    }
//
//    private static Connection createConnection() {
//        Connection con = null;
//        try {
//            con = DriverManager.getConnection(url, user, password);
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in connection pool", e);
//        }
//        return con;
//    }



}

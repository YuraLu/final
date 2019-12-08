package by.epam.lukashevich.dao.core.pool;


import by.epam.lukashevich.dao.core.pool.connection.ProxyConnection;

public interface ConnectionPool {

    ProxyConnection getConnection();

    void putBackConnection(ProxyConnection connection);

    void destroyPool();
}
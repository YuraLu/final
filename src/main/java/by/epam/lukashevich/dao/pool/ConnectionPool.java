package by.epam.lukashevich.dao.pool;


import by.epam.lukashevich.dao.pool.connection.ProxyConnection;

public interface ConnectionPool {

    ProxyConnection getConnection();

    void putBackConnection(ProxyConnection connection);

    void destroyPool();
}


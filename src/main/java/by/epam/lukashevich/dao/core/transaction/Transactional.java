package by.epam.lukashevich.dao.core.transaction;

import by.epam.lukashevich.dao.core.pool.connection.ConnectionWrapper;

import java.sql.SQLException;

/**
 * Represents Transactional class which give us possibility
 * to use transactions with auto rollback.
 *
 * @author Yuri Lukashevich
 * @version 1.0
 * @since JDK1.0
 */
public class Transactional implements AutoCloseable {

    private boolean isCommit = false;
    private final ConnectionWrapper connectionWrapper;

    public Transactional(ConnectionWrapper connectionWrapper) throws SQLException {
        this.connectionWrapper = connectionWrapper;
        setAutoCommitStatus(false);
    }

    public void commit() throws SQLException {
        this.connectionWrapper.commit();
        this.isCommit = true;
    }

    public void rollBack() throws SQLException {
        this.connectionWrapper.rollback();
    }

    @Override
    public void close() throws SQLException {
        if (!isCommit) {
            rollBack();
        }
        setAutoCommitStatus(true);
    }

    private void setAutoCommitStatus(boolean commitStatus) throws SQLException {
        this.connectionWrapper.setAutoCommit(commitStatus);
    }
}
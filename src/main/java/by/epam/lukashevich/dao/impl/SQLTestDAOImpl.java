package by.epam.lukashevich.dao.impl;

import by.epam.lukashevich.dao.TestDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.dao.pool.connection.ConnectionWrapper;
import by.epam.lukashevich.dao.pool.connection.ProxyConnection;
import by.epam.lukashevich.dao.pool.impl.DatabaseConnectionPool;
import by.epam.lukashevich.dao.util.SQLUtil;
import by.epam.lukashevich.domain.entity.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lukashevich.dao.util.SQLQuery.*;

public class SQLTestDAOImpl implements TestDAO {

    private final DatabaseConnectionPool pool = DatabaseConnectionPool.getInstance();

    @Override
    public List<Test> findAll() throws DAOException {
        List<Test> list = new ArrayList<>();
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_ALL_TESTS)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Test test = SQLUtil.getTest(rs);
                list.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("SQL Exception can't create list of tests in findAll()", e);
        }
        return list;
    }

    @Override
    public Test findById(Integer id) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_TEST_BY_ID)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return SQLUtil.getTest(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't find test in findById()", e);
        }
        throw new DAOException("No test with ID=" + id + ".");
    }

    @Override
    public boolean add(Test test) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(ADD_NEW_TEST)) {

            if (isTitleUsed(con, test.getTitle())) {
                throw new DAOException("Title is already is use!");
            }
            st.setString(1, test.getTitle());
            st.setString(2, test.getDescription());
            st.setInt(3, test.getSubject().getId());
            st.setInt(4, test.getAuthor().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during registration()", e);
        }
        return true;
    }

    @Override
    public boolean update(Test test) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(UPDATE_TEST)) {

            st.setString(1, test.getTitle());
            st.setString(2, test.getDescription());
            st.setInt(3, test.getSubject().getId());
            st.setInt(4, test.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during test update()", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(DELETE_TEST)) {
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't delete test with id=" + id, e);
        }
    }

    private boolean isTitleUsed(Connection connection, String title) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(GET_TEST_BY_TITLE)) {
            st.setString(1, title);
            ResultSet rs = st.executeQuery();
            return rs.first();
        }
    }

}

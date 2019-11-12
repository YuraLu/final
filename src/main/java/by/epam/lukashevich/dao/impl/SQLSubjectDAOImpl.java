package by.epam.lukashevich.dao.impl;

import by.epam.lukashevich.dao.SubjectDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.dao.pool.connection.ConnectionWrapper;
import by.epam.lukashevich.dao.pool.connection.ProxyConnection;
import by.epam.lukashevich.dao.pool.impl.DatabaseConnectionPool;
import by.epam.lukashevich.dao.util.SQLUtil;
import by.epam.lukashevich.domain.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lukashevich.dao.util.SQLQuery.*;

public class SQLSubjectDAOImpl implements SubjectDAO {

    private final DatabaseConnectionPool pool = DatabaseConnectionPool.getInstance();

    @Override
    public List<Subject> findAll() throws DAOException {
        List<Subject> list = new ArrayList<>();
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_ALL_SUBJECTS)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Subject subject = SQLUtil.getSubject(rs);
                list.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("SQL Exception can't create list of subjects in findAll()", e);
        }
        return list;
    }

    @Override
    public Subject findById(Integer id) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_SUBJECT_BY_ID)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return SQLUtil.getSubject(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't find subject in findById()", e);
        }
        throw new DAOException("No subject with ID=" + id + ".");
    }

    @Override
    public boolean add(Subject subject) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(ADD_NEW_SUBJECT)) {

            if (isNameUsed(con, subject.getName())) {
                throw new DAOException("Subject name is already is use!");
            }
            st.setString(1, subject.getName());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during subject add()", e);
        }
        return true;
    }

    @Override
    public boolean update(Subject subject) throws DAOException {
        return false;
    }


    @Override
    public boolean delete(Integer id) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(DELETE_SUBJECT)) {

            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't delete subject with id=" + id, e);
        }
    }

    private boolean isNameUsed(Connection connection, String name) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(GET_SUBJECT_BY_NAME)) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            return rs.first();
        }
    }
}

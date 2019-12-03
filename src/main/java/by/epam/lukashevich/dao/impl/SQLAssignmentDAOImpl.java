package by.epam.lukashevich.dao.impl;

import by.epam.lukashevich.dao.AssignmentDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.dao.pool.connection.ConnectionWrapper;
import by.epam.lukashevich.dao.pool.connection.ProxyConnection;
import by.epam.lukashevich.dao.pool.impl.DatabaseConnectionPool;
import by.epam.lukashevich.dao.util.SQLUtil;
import by.epam.lukashevich.domain.entity.Assignment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lukashevich.dao.util.SQLQuery.*;

public class SQLAssignmentDAOImpl implements AssignmentDAO {

    private final DatabaseConnectionPool pool = DatabaseConnectionPool.getInstance();

    @Override
    public List<Assignment> findAll() throws DAOException {
        List<Assignment> list = new ArrayList<>();
        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_ALL_ASSIGNMENTS)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Assignment assignment = SQLUtil.getAssignment(rs);
                list.add(assignment);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't create list of assignments in findAll()", e);
        }
        return list;
    }

    @Override
    public Assignment findById(Integer id) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_ASSIGNMENT_BY_ID)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return SQLUtil.getAssignment(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't find assignment in findById()", e);
        }
        throw new DAOException("No assignment with ID=" + id + ".");
    }

    @Override
    public boolean add(Assignment assignment) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(ADD_NEW_ASSIGNMENT)) {

            st.setInt(1, assignment.getTest().getId());
            st.setInt(2, assignment.getStudent().getId());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during add()", e);
        }
    }

    @Override
    public boolean update(Assignment assignment) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(UPDATE_ASSIGNMENT)) {

            st.setInt(1, assignment.getScore());
            st.setInt(2, assignment.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during update()", e);
        }
    }


    @Override
    public boolean delete(Integer id) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(DELETE_ASSIGNMENT)) {
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't delete assignment with id=" + id, e);
        }
    }

    @Override
    public int addAndReturnId(Assignment assignment) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(ADD_NEW_ASSIGNMENT, Statement.RETURN_GENERATED_KEYS)) {

            st.setInt(1, assignment.getTest().getId());
            st.setInt(2, assignment.getStudent().getId());
            st.executeUpdate();

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new DAOException("SQL Exception during add()", e);
        }
    }

    @Override
    public int getAssignmentScore(Assignment assignment) throws DAOException {
        return 0;
    }
}

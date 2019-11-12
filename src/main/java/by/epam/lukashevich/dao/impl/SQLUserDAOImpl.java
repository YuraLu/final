package by.epam.lukashevich.dao.impl;

import by.epam.lukashevich.dao.UserDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.dao.pool.connection.ConnectionWrapper;
import by.epam.lukashevich.dao.pool.connection.ProxyConnection;
import by.epam.lukashevich.dao.pool.impl.DatabaseConnectionPool;
import by.epam.lukashevich.dao.util.SQLUtil;
import by.epam.lukashevich.domain.entity.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lukashevich.dao.util.SQLQuery.*;

public class SQLUserDAOImpl implements UserDAO {

    private final DatabaseConnectionPool pool = DatabaseConnectionPool.getInstance();

    @Override
    public List<User> findAll() throws DAOException {

        List<User> users = new ArrayList<>();
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_ALL_USERS)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User user = SQLUtil.getUser(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("SQL Exception can't create list of users in findAll()", e);
        }
        return users;
    }

    @Override
    public User findById(Integer id) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_USER_BY_ID)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return SQLUtil.getUser(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't find user in findById()", e);
        }
        throw new DAOException("No user with ID=" + id + ".");
    }

    @Override
    public boolean add(User user) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(ADD_NEW_USER)) {

            if (isLoginUsed(con, user.getLogin())) {
                throw new DAOException("Login is already is use!");
            }

            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getLogin());
            st.setString(4, user.getPassword());
            st.setInt(5, user.getRole().getId());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during registration()", e);
        }
    }

    @Override
    public User authorization(String login, String password) throws DAOException {
        User user = null;
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_USER_BY_LOGIN_PASS)) {

            st.setString(1, login);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                user = SQLUtil.getUser(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during registration()", e);
        }
        return user;
    }

    @Override
    public boolean updateUserPassword(String login, String newPassword) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(DELETE_USER)) {
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't delete user with id=" + id, e);
        }
    }

    @Override
    public boolean update(User user) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(UPDATE_USER)) {

            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getLogin());
            st.setString(4, user.getPassword());
            st.setInt(5, user.getRole().getId());
            st.setBoolean(6, user.getBanned());
            st.setInt(7, user.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during update()", e);
        }
    }

    @Override
    public void updateBanStatus(Integer id) throws DAOException {
        final User user = findById(id);
        final boolean isBanned = !user.getBanned();

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(UPDATE_USER_BAN_STATUS)) {

            st.setBoolean(1, isBanned);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during update Ban status()", e);
        }
    }

    @Override
    public void updateStatus(Integer id) throws DAOException {
        final User user = findById(id);
        int roleId = user.getRole().getId();
        if (roleId == 2) {
            roleId = 3;
        } else {
            roleId = 2;
        }

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(UPDATE_USER_STATUS)) {

            st.setInt(1, roleId);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during update status()", e);
        }
    }

    private boolean isLoginUsed(Connection connection, String login) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(GET_USER_BY_LOGIN)) {
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            return rs.first();
        }
    }
}
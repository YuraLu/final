package by.epam.trjava.tutorsystem.dao.impl;

import by.epam.trjava.tutorsystem.dao.UserDAO;
import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.dao.pool.ConnectionPool;
import by.epam.trjava.tutorsystem.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.trjava.tutorsystem.dao.impl.SQLQuery.*;

public class SQLUserDAO implements UserDAO {

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    public SQLUserDAO() {
    }

    @Override
    public List<User> findAll() throws DAOException {
        List<User> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(QUERY_GET_ALL_USER);
            resultSet = st.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);
                user.setLogin(login);
                user.setPassword(password);

                list.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't find users in findAll()", e);
        } finally {
            pool.closeConnection(resultSet, st, con);
        }
        return list;
    }

    @Override
    public User authorization(String login, String password) throws DAOException {
        User user = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = pool.getConnection();
            st = con.prepareStatement(QUERY_CHECK_USER);
            st.setString(1, login);
            st.setString(2, password);
            rs = st.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String userLogin = rs.getString("login");
                String userPassword = rs.getString("password");

                user = new User();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);
                user.setLogin(userLogin);
                user.setPassword(userPassword);
            } else throw new DAOException("Check please login and password");
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during authorization", e);
        } finally {
            pool.closeConnection(rs, st, con);
        }
        return user;
    }

    @Override
    public boolean registration(String userLogin, String userPassword, String userEmail, String userName) throws DAOException {
        Connection con = null;
        PreparedStatement stCheck = null;
        PreparedStatement stRegister = null;
        ResultSet rs = null;
        boolean result;
        try {
            con = pool.getConnection();
            con.setAutoCommit(false);
            stCheck = con.prepareStatement(QUERY_USER_EXISTS_CHECK);
            stRegister = con.prepareStatement(QUERY_REGISTER_USER);
            stCheck.setString(1, userLogin);
            rs = stCheck.executeQuery();

            if (!rs.next()) {
                stRegister.setString(1, userEmail);
                stRegister.setString(2, userLogin);
                stRegister.setString(3, userPassword);
                stRegister.setString(4, userName);
                result = stRegister.executeUpdate() > 0;
            } else {
                throw new DAOException("User exists during registration()");
            }
            con.commit();
        } catch (
                SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new DAOException("SQL Exception during rollback in registration()", e1);
            }
            throw new DAOException("SQL Exception during registration()", e);
        } finally {
            pool.closeConnection(rs, stCheck, stRegister, con);
        }
        return result;
    }

    @Override
    public User findById(int userId) throws DAOException {
        User user = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(QUERY_GET_USER);
            st.setInt(1, userId);
            rs = st.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String login = rs.getString("login");
                String password = rs.getString("password");

                user = new User();
                user.setId(userId);
                user.setName(name);
                user.setEmail(email);
                user.setLogin(login);
                user.setPassword(password);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't find user in findById()", e);
        } finally {
            pool.closeConnection(rs, st, con);
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws DAOException {
        User user = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(QUERY_GET_USER_BY_EMAIL);
            st.setString(1, email);
            rs = st.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String login = rs.getString("login");
                String password = rs.getString("password");

                user = new User();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);
                user.setLogin(login);
                user.setPassword(password);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't find user by email findByEmail()", e);
        } finally {
            pool.closeConnection(rs, st, con);
        }
        return user;
    }

    @Override
    public boolean delete(String userId) throws DAOException {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        try {
            con = pool.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(QUERY_GET_USER);
            ps.setString(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                ps2 = con.prepareStatement(QUERY_DELETE_USER);
                ps2.setString(1, userId);
                rowUpdated = ps2.executeUpdate() > 0;
            } else {
                throw new DAOException("Can't delete user in delete()");
            }
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new DAOException("SQL Exception during rollback in delete()", e1);
            }
            throw new DAOException("SQL Exception during delete()", e);
        } finally {
            pool.closeConnection(rs, ps, ps2, con);
        }
        return rowUpdated;
    }

    @Override
    public boolean updateUserPassword(String login, String newPassword) throws DAOException {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;
        try {
            con = pool.getConnection();
            st = con.prepareStatement(QUERY_UPDATE_PASSWORD);
            st.setString(1, newPassword);
            st.setString(2, login);
            rowUpdated = st.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during updateUserPassword()", e);
        } finally {
            pool.closeConnection(resultSet, st, con);
        }
        return rowUpdated;
    }
}
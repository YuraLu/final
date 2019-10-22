package by.epam.trjava.tutorsystem.dao.impl;

import by.epam.trjava.tutorsystem.dao.UserDAO;
import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.dao.pool.ConnectionPool;
import by.epam.trjava.tutorsystem.dao.pool.JDBCUtils;
import by.epam.trjava.tutorsystem.dao.pool.exception.ConnectionPoolException;
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
        ResultSet rs = null;

        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_GET_ALL_USER);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String name = rs.getString("name");
                int roleId = rs.getInt("roleId");

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);
                user.setLogin(login);
                user.setPassword(password);
                user.setRoleId(roleId);

                list.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't find users in findAll()", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
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
            con = pool.takeConnection();
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
                int roleId = rs.getInt("roleId");

                user = new User();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);
                user.setLogin(userLogin);
                user.setPassword(userPassword);
                user.setRoleId(roleId);
            } else {
                throw new DAOException("Check please login and password");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during authorization", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return user;
    }

//    @Override
//    public boolean registration(String userLogin, String userPassword, String userEmail, String userName, int userRoleId) throws DAOException {
//        Connection con = null;
//        PreparedStatement st = null;
//        ResultSet rs = null;
//        boolean result;
//        try {
//
//            if (isLoginExists(userLogin)) {
//                throw new DAOException("User login already exists");
//            }
//
//            con = pool.getConnection();
//            st = con.prepareStatement(QUERY_REGISTER_USER);
//            st.setString(1, userEmail);
//            st.setString(2, userLogin);
//            st.setString(3, userPassword);
//            st.setString(4, userName);
//            st.setInt(5, userRoleId);
//            result = st.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new DAOException("SQL Exception during registration()", e);
//        } finally {
//            ConnectionPoolUtil.closers, st, con);
//        }
//        return result;
//    }


    @Override
    public boolean registration(String userLogin, String userPassword, String userEmail, String userName, int userRoleId) throws DAOException {
        Connection con = null;
        PreparedStatement stCheck = null;
        PreparedStatement stRegister = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = pool.takeConnection();
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
                stRegister.setInt(5, userRoleId);
                result = stRegister.executeUpdate() > 0;
            } else {
                throw new DAOException("User exists during registration()");
            }
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new DAOException("SQL Exception during rollback in registration()", e1);
            }
            throw new DAOException("SQL Exception during registration()", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stCheck, stRegister, con);
//            pool.closeConnection(rs, stCheck, stRegister, con);
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
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_GET_USER);
            st.setInt(1, userId);
            rs = st.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String login = rs.getString("login");
                String password = rs.getString("password");
                int roleId = rs.getInt("roleId");

                user = new User();
                user.setId(userId);
                user.setName(name);
                user.setEmail(email);
                user.setLogin(login);
                user.setPassword(password);
                user.setRoleId(roleId);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't find user in findById()", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return user;
    }

    @Override
    public boolean delete(int userId) throws DAOException {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(QUERY_GET_USER);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                ps2 = con.prepareStatement(QUERY_DELETE_USER);
                ps2.setInt(1, userId);
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
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
//            JDBCUtils.close(rs, ps, ps2, con);
            pool.closeConnection(rs, ps, ps2, con);
        }
        return rowUpdated;
    }

    @Override
    public boolean updateUserPassword(String login, String newPassword) throws DAOException {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_UPDATE_PASSWORD);
            st.setString(1, newPassword);
            st.setString(2, login);
            rowUpdated = st.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during updateUserPassword()", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return rowUpdated;
    }

    private boolean isLoginExists(String login) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_USER_EXISTS_CHECK);
            st.setString(1, login);
            rs = st.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during checkLogin()", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return result;
    }
}
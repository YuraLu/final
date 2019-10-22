package by.epam.trjava.tutorsystem.dao.impl;

import by.epam.trjava.tutorsystem.dao.RoleDAO;
import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.dao.pool.ConnectionPool;
import by.epam.trjava.tutorsystem.dao.pool.JDBCUtils;
import by.epam.trjava.tutorsystem.dao.pool.exception.ConnectionPoolException;
import by.epam.trjava.tutorsystem.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.trjava.tutorsystem.dao.impl.SQLQuery.*;

public class SQLRoleDAO implements RoleDAO {

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    public SQLRoleDAO() {
    }

    @Override
    public List<Role> findAll() throws DAOException {
        List<Role> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_GET_ALL_ROLE);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Role role = new Role();
                role.setId(id);
                role.setName(name);
                list.add(role);
            }
        } catch (SQLException e) {
            throw new DAOException("Can't make list of roles", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return list;
    }

    @Override
    public boolean add(Role newRole) throws DAOException {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_ADD_ROLE);
            st.setString(1, newRole.getName());
            rowUpdated = st.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Can't add role", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return rowUpdated;
    }

    @Override
    public Role findById(int roleId) throws DAOException {
        Role role = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_GET_ROLE);
            st.setInt(1, roleId);
            rs = st.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");

                role = new Role();
                role.setId(roleId);
                role.setName(name);
            }
        } catch (SQLException e) {
            throw new DAOException("Can't find role by id", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return role;
    }

    @Override
    public Role findRoleIdByName(String roleName) throws DAOException {
        Role role = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_GET_ROLE_BY_NAME);
            st.setString(1, roleName.toUpperCase());
            rs = st.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                role = new Role();
                role.setId(id);
                role.setName(roleName);
            }
        } catch (SQLException e) {
            throw new DAOException("Can't find roleId by name = " + roleName, e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return role;
    }

    @Override
    public boolean delete(int roleId) throws DAOException {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_GET_ROLE);
            ps.setInt(1, roleId);
            rs = ps.executeQuery();

            if (rs.next()) {
                ps2 = con.prepareStatement(QUERY_DELETE_ROLE);
                ps2.setInt(1, roleId);
                rowUpdated = ps2.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            throw new DAOException("Can't delete Role with id=" + roleId, e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, ps2, ps, con);
//            pool.closeConnection(rs, ps2, ps, con);
        }
        return rowUpdated;
    }
}

package by.epam.trjava.tutorsystem.dao.impl;

import by.epam.trjava.tutorsystem.dao.TestDAO;
import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.dao.pool.ConnectionPool;
import by.epam.trjava.tutorsystem.dao.pool.JDBCUtils;
import by.epam.trjava.tutorsystem.dao.pool.exception.ConnectionPoolException;
import by.epam.trjava.tutorsystem.entity.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.trjava.tutorsystem.dao.impl.SQLQuery.*;

public class SQLTestDAO implements TestDAO {

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    public SQLTestDAO() {
    }

    @Override
    public List<Test> findAll() throws DAOException {
        List<Test> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_GET_ALL_TEST);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int subjectId = rs.getInt("subjectId");
                int authorId = rs.getInt("authorId");

                Test test = new Test();
                test.setId(id);
                test.setTitle(title);
                test.setDescription(description);
                test.setSubjectId(subjectId);
                test.setAuthorId(authorId);

                list.add(test);
            }
        } catch (SQLException e) {
            throw new DAOException("Problem to get list of tests", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return list;
    }


    @Override
    public Test findById(int testId) throws DAOException {
        Test test = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_GET_TEST);
            st.setInt(1, testId);
            rs = st.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int subjectId = rs.getInt("subjectId");
                int authorId = rs.getInt("authorId");

                test = new Test();
                test.setId(id);
                test.setTitle(title);
                test.setDescription(description);
                test.setSubjectId(subjectId);
                test.setAuthorId(authorId);
            }
        } catch (SQLException e) {
            throw new DAOException("Can't make statement for Test with id=" + testId, e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return test;
    }

    @Override
    public boolean add(Test newTest) throws DAOException {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_ADD_TEST);
            st.setString(1, newTest.getTitle());
            st.setString(2, newTest.getDescription());
            st.setInt(3, newTest.getSubjectId());
            st.setInt(4, newTest.getAuthorId());
            rowUpdated = st.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Can't add Test with id=" + newTest.getId(), e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return rowUpdated;
    }

    @Override
    public boolean update(Test test) throws DAOException {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_UPDATE_TEST);
            st.setString(1, test.getTitle());
            st.setString(2, test.getDescription());
            st.setInt(3, test.getSubjectId());
            st.setInt(4, test.getId());
            rowUpdated = st.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DAOException("Can't update Test with id=" + test.getId(), e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int testId) throws DAOException {
        boolean rowDeleted = false;
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            con = pool.takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(QUERY_GET_TEST);
            ps.setInt(1, testId);
            rs = ps.executeQuery();

            if (rs.next()) {
                ps2 = con.prepareStatement(QUERY_DELETE_TEST);
                ps2.setInt(1, testId);
                con.commit();
                rowDeleted = ps2.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            throw new DAOException("Can't delete Test with id=" + testId, e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, ps2, ps, con);
//            pool.closeConnection(rs, ps2, ps, con);
        }
        return rowDeleted;
    }
}

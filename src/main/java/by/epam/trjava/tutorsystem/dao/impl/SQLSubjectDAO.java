package by.epam.trjava.tutorsystem.dao.impl;

import by.epam.trjava.tutorsystem.dao.SubjectDAO;
import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.dao.pool.ConnectionPool;
import by.epam.trjava.tutorsystem.dao.pool.JDBCUtils;
import by.epam.trjava.tutorsystem.dao.pool.exception.ConnectionPoolException;
import by.epam.trjava.tutorsystem.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.trjava.tutorsystem.dao.impl.SQLQuery.*;

public class SQLSubjectDAO implements SubjectDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    public SQLSubjectDAO() {
    }

    @Override
    public List<Subject> findAll() throws DAOException {
        List<Subject> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_GET_ALL_SUBJECT);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Subject subject = new Subject();
                subject.setId(id);
                subject.setName(name);
                list.add(subject);
            }
        } catch (SQLException e) {
            throw new DAOException("Problem to get list of subjects", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);

        }
        return list;
    }

    @Override
    public Subject findById(int subjectId) throws DAOException {
        Subject subject = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_GET_SUBJECT);
            st.setInt(1, subjectId);
            rs = st.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");

                subject = new Subject();
                subject.setId(subjectId);
                subject.setName(name);
            }
        } catch (SQLException e) {
            throw new DAOException("Can't find Subject with id=" + subjectId, e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);

        }
        return subject;
    }

    @Override
    public boolean add(Subject newSubject) throws DAOException {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_ADD_SUBJECT);
            st.setString(1, newSubject.getName());
            rowUpdated = st.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Can't add Subject", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);

        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int subjectId) throws DAOException {
        boolean rowDeleted = false;
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            con = pool.takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(QUERY_GET_SUBJECT);
            ps.setInt(1, subjectId);
            rs = ps.executeQuery();

            if (rs.next()) {
                ps2 = con.prepareStatement(QUERY_DELETE_SUBJECT);
                ps2.setInt(1, subjectId);
                con.commit();
                rowDeleted = ps2.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            throw new DAOException("Can't delete Subject with id=" + subjectId, e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, ps2, ps, con);
//            pool.closeConnection(rs, ps2, ps, con);

        }
        return rowDeleted;
    }
}

package by.epam.trjava.tutorsystem.dao.impl;

import by.epam.trjava.tutorsystem.dao.SubjectDAO;
import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.dao.pool.ConnectionPool;
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
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(QUERY_GET_ALL_SUBJECT);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

//                Subject subject = new Subject(id, name);
//                list.add(subject);
            }
        } catch (SQLException e) {
            throw new DAOException("Problem to get list of tests", e);
        } finally {
            pool.closeConnection(rs, st, con);
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
            con = pool.getConnection();
            st = con.prepareStatement(QUERY_GET_SUBJECT);
            st.setInt(1, subjectId);
            rs = st.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

//                subject = new Subject(id, name);
            }

        } catch (SQLException e) {
            throw new DAOException("Can't make statement for Subject with id=" + subjectId, e);
        } finally {
            pool.closeConnection(rs, st, con);
        }
        return subject;
    }

    @Override
    public boolean add(Subject newSubject) throws DAOException {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;

        try {
            con = pool.getConnection();
            st = con.prepareStatement(QUERY_ADD_SUBJECT);
            st.setString(1, newSubject.getName());
            rowUpdated = st.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            pool.closeConnection(resultSet, st, con);
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
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_GET_SUBJECT);
            ps.setInt(1, subjectId);
            rs = ps.executeQuery();

            if (rs.next()) {
                ps2 = con.prepareStatement(QUERY_DELETE_SUBJECT);
                ps2.setInt(1, subjectId);
                rowDeleted = ps2.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.closeConnection(rs, ps2, ps, con);
        }
        return rowDeleted;
    }
}

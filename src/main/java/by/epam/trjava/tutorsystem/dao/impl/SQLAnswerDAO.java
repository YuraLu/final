package by.epam.trjava.tutorsystem.dao.impl;

import by.epam.trjava.tutorsystem.dao.AnswerDAO;
import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.dao.pool.JDBCUtils;
import by.epam.trjava.tutorsystem.dao.pool.ConnectionPool;
import by.epam.trjava.tutorsystem.dao.pool.exception.ConnectionPoolException;
import by.epam.trjava.tutorsystem.entity.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.trjava.tutorsystem.dao.impl.SQLQuery.*;

public class SQLAnswerDAO implements AnswerDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    public SQLAnswerDAO() {
    }

    @Override
    public List<Answer> findAll() throws DAOException {
        List<Answer> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_GET_ALL_ANSWER);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String text = rs.getString("text");
                int isCorrect = rs.getInt("isCorrect");

                Answer answer = new Answer();
                answer.setId(id);
                answer.setText(text);
                answer.setIsCorrect(isCorrect);

                list.add(answer);
            }
        } catch (SQLException e) {
            throw new DAOException("Problem to get list of Answers", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return list;
    }

    @Override
    public Answer findById(int answerId) throws DAOException {
        Answer answer = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_GET_ANSWER);
            st.setInt(1, answerId);
            rs = st.executeQuery();

            if (rs.next()) {
                String text = rs.getString("text");
                int isCorrect = rs.getInt("isCorrect");

                answer = new Answer();
                answer.setId(answerId);
                answer.setText(text);
                answer.setIsCorrect(isCorrect);
            }
        } catch (SQLException e) {
            throw new DAOException("Can't make statement for Answer with id=" + answerId, e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return answer;
    }

    @Override
    public boolean add(Answer newAnswer) throws DAOException {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_ADD_ANSWER);
            st.setString(1, newAnswer.getText());
            st.setInt(2, newAnswer.getIsCorrect());
            rowUpdated = st.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Can't add Answer with id=" + newAnswer.getId(), e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int answerId) throws DAOException {
        boolean rowDeleted = false;
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            con = pool.takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(QUERY_GET_ANSWER);
            ps.setInt(1, answerId);
            rs = ps.executeQuery();

            if (rs.next()) {
                ps2 = con.prepareStatement(QUERY_DELETE_ANSWER);
                ps2.setInt(1, answerId);
                con.commit();
                rowDeleted = ps2.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            throw new DAOException("Can't delete Answer with id=" + answerId, e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, ps2, ps, con);
//            pool.closeConnection(rs, ps2, ps, con);
        }
        return rowDeleted;
    }
}

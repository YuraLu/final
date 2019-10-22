package by.epam.trjava.tutorsystem.dao.impl;

import by.epam.trjava.tutorsystem.dao.QuestionDAO;
import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.dao.pool.JDBCUtils;
import by.epam.trjava.tutorsystem.dao.pool.ConnectionPool;
import by.epam.trjava.tutorsystem.dao.pool.exception.ConnectionPoolException;
import by.epam.trjava.tutorsystem.entity.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.trjava.tutorsystem.dao.impl.SQLQuery.*;

public class SQLQuestionDAO implements QuestionDAO {

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    public SQLQuestionDAO() {
    }

    @Override
    public List<Question> findAll() throws DAOException {
        List<Question> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_GET_ALL_QUESTION);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String text = rs.getString("text");

                Question question = new Question();
                question.setId(id);
                question.setText(text);

                list.add(question);
            }
        } catch (SQLException e) {
            throw new DAOException("Problem to get list of questions", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);

        }
        return list;
    }

    @Override
    public boolean add(Question newQuestion) throws DAOException {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_ADD_QUESTION);
            st.setString(1, newQuestion.getText());
            rowUpdated = st.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Can't add Question with id=" + newQuestion.getId(), e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);

        }
        return rowUpdated;
    }

    public boolean update(Question question) throws DAOException {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_UPDATE_QUESTION);
            st.setString(1, question.getText());
            rowUpdated = st.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DAOException("Can't update Question", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);

        }
        return rowUpdated;
    }

    @Override
    public Question findById(int questionId) throws DAOException {
        Question question = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_GET_QUESTION);
            st.setInt(1, questionId);
            rs = st.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String text = rs.getString("text");

                question = new Question();
                question.setId(questionId);
                question.setText(text);
            }
        } catch (SQLException e) {
            throw new DAOException("Can't make statement for Question with id=" + questionId, e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);

        }
        return question;
    }

    @Override
    public boolean delete(int questionId) throws DAOException {
        boolean rowDeleted = false;
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            con = pool.takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(QUERY_GET_QUESTION);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();

            if (rs.next()) {
                ps2 = con.prepareStatement(QUERY_DELETE_QUESTION);
                ps2.setInt(1, questionId);
                con.commit();
                rowDeleted = ps2.executeUpdate() > 0;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Can't delete Question with id=" + questionId, e);
        } finally {
            JDBCUtils.close(rs, ps2, ps, con);
//            pool.closeConnection(rs, ps2, ps,con);

        }
        return rowDeleted;
    }
}

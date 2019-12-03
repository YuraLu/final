package by.epam.lukashevich.dao.impl;

import by.epam.lukashevich.dao.AnswerDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.dao.pool.connection.ConnectionWrapper;
import by.epam.lukashevich.dao.pool.connection.ProxyConnection;
import by.epam.lukashevich.dao.pool.impl.DatabaseConnectionPool;
import by.epam.lukashevich.dao.util.SQLUtil;
import by.epam.lukashevich.domain.entity.Answer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lukashevich.dao.util.SQLQuery.*;

public class SQLAnswerDAOImpl implements AnswerDAO {

    private final DatabaseConnectionPool pool = DatabaseConnectionPool.getInstance();

    @Override
    public List<Answer> findAll() throws DAOException {
        List<Answer> list = new ArrayList<>();
        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_ALL_ANSWERS)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Answer answer = SQLUtil.getAnswer(rs);
                list.add(answer);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't create list of answers in findAll()", e);
        }
        return list;
    }

    @Override
    public Answer findById(Integer id) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_ANSWER_BY_ID)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return SQLUtil.getAnswer(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't find answer in findById()", e);
        }
        throw new DAOException("No answer with ID=" + id + ".");
    }

    @Override
    public boolean add(Answer answer) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(ADD_NEW_ANSWER)) {

            if (isTextUsed(con, answer.getAnswerText())) {
                throw new DAOException("Text of answer is already in use!");
            }
            st.setString(1, answer.getAnswerText());
            st.setBoolean(2, answer.getIsCorrect());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during add()", e);
        }
    }

    @Override
    public boolean update(Answer answer) throws DAOException {
//        try (ProxyConnection proxyConnection = pool.getConnection();
//             Connection con = proxyConnection.getConnectionWrapper();
//             PreparedStatement st = con.prepareStatement(UPDATE_ANSWER)) {
//
//            st.setString(1, answer.getAnswerText());
//            st.setBoolean(2, answer.getIsCorrect());
//            st.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            throw new DAOException("SQL Exception during answer update()", e);
//        }
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(DELETE_ANSWER)) {
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't delete answer with id=" + id, e);
        }
    }

    @Override
    public int addAndReturnId(Answer answer) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(ADD_NEW_ANSWER, Statement.RETURN_GENERATED_KEYS)) {

//            if (isTextUsed(con, answer.getAnswerText())) {
//                throw new DAOException("Text of question is already in use!");
//            }
            st.setString(1, answer.getAnswerText());
            st.setBoolean(2, answer.getIsCorrect());
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
    public List<Answer> findAllAnswersForQuestionId(int questionId) throws DAOException {
        List<Answer> list = new ArrayList<>();
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_ALL_ANSWERS_BY_QUESTION_ID)) {

            st.setInt(1,questionId);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Answer answer = SQLUtil.getAnswer(rs);
                list.add(answer);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't create list of answers in findAll() AnswerGroupDAOImpl", e);
        }
        return list;
    }

    @Override
    public List<Integer> addAnswerList(List<Answer> answers) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(ADD_NEW_ANSWER, Statement.RETURN_GENERATED_KEYS)) {

            con.setAutoCommit(false);

            for (int i = 0; i < answers.size(); i++) {
                st.setString(1, answers.get(i).getAnswerText());
                st.setBoolean(2, answers.get(i).getIsCorrect());
                st.addBatch();
            }
            st.executeBatch();
            con.commit();
            con.setAutoCommit(true);

            List<Integer> idsList = new ArrayList<>();
            ResultSet rs = st.getGeneratedKeys();

            if (rs != null) {
                while (rs.next()) {
                    idsList.add(rs.getInt(1));
                }
            } else {
                throw new SQLException("No ID obtained.");
            }

            return idsList;

        } catch (SQLException e) {
            throw new DAOException("SQL Exception during add()", e);
        }
    }

    private boolean isTextUsed(Connection connection, String text) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(GET_ANSWER_BY_TEXT)) {
            st.setString(1, text);
            ResultSet rs = st.executeQuery();
            return rs.first();
        }
    }
}

package by.epam.lukashevich.dao.impl;

import by.epam.lukashevich.dao.QuestionDAO;
import by.epam.lukashevich.dao.core.pool.connection.ConnectionWrapper;
import by.epam.lukashevich.dao.core.pool.connection.ProxyConnection;
import by.epam.lukashevich.dao.core.pool.impl.DatabaseConnectionPool;
import by.epam.lukashevich.dao.core.transaction.Transactional;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.dao.impl.util.SQLUtil;
import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.entity.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.lukashevich.dao.impl.util.SQLQuery.*;

public class SQLQuestionDAOImpl implements QuestionDAO {

    private final DatabaseConnectionPool pool = DatabaseConnectionPool.getInstance();

    @Override
    public List<Question> findAll() throws DAOException {

        List<Question> questionList;

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_ALL_QUESTIONS)) {

            ResultSet rs = st.executeQuery();
            questionList = getQuestionList(rs);

        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't create list of questions in findAll()", e);
        }
        return questionList;
    }

    @Override
    public Question findById(Integer id) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_QUESTION_BY_ID)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            return getQuestionList(rs).get(0);

        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't find question in findById()", e);
        }
    }

    @Override
    public boolean add(Question question) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(ADD_NEW_QUESTION)) {

            if (isTextUsed(con, question.getQuestionText())) {
                throw new DAOException("Text of question is already in use!");
            }

            st.setString(1, question.getQuestionText());
            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during add()", e);
        }
    }

    @Override
    public boolean update(Question question) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(DELETE_QUESTION)) {

            st.setInt(1, id);
            st.executeUpdate();

            return true;

        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't delete question with id=" + id, e);
        }
    }

    @Override
    public int addAndReturnId(Question question) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(ADD_NEW_QUESTION, Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, question.getQuestionText());
            st.executeUpdate();

            ResultSet generatedKeys = st.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new DAOException("No ID obtained.");
            }

        } catch (SQLException e) {
            throw new DAOException("SQL Exception during add()", e);
        }
    }

    @Override
    public List<Integer> addAnswersList(int questionId, List<Integer> answerIdsList) throws DAOException {

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             Transactional transactional = new Transactional(con);
             PreparedStatement st = con.prepareStatement(ADD_ANSWERS_LIST_FOR_QUESTION_ID,
                     Statement.RETURN_GENERATED_KEYS)) {

            for (Integer answerId : answerIdsList) {
                st.setInt(1, answerIdsList.get(answerId));
                st.setInt(2, questionId);
                st.addBatch();
            }

            st.executeBatch();
            transactional.commit();

            return SQLUtil.getIdList(st);

        } catch (SQLException e) {
            throw new DAOException("SQL Exception during add()", e);
        }
    }

    @Override
    public List<Integer> addQuestionList(List<Question> questions) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             Transactional transactional = new Transactional(con);
             PreparedStatement st = con.prepareStatement(ADD_NEW_QUESTION, Statement.RETURN_GENERATED_KEYS)) {

            for (Question question : questions) {
                st.setString(1, question.getQuestionText());
                st.addBatch();
            }

            st.executeBatch();
            transactional.commit();

            return SQLUtil.getIdList(st);

        } catch (SQLException e) {
            throw new DAOException("SQL Exception during add()", e);
        }
    }

    @Override
    public List<Question> findAllQuestionsForTestId(int testId) throws DAOException {

        List<Question> list = new ArrayList<>();

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_ALL_QUESTIONS_BY_TEST_ID)) {

            st.setInt(1, testId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Question question = SQLUtil.getQuestion(rs);
                list.add(question);
            }

        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't create list of questions " +
                    "in findAllQuestionsForTestId() SQLQuestionDAOImpl", e);
        }
        return list;
    }

    private List<Question> getQuestionList(ResultSet resultSet) throws SQLException {

        List<Question> questionList = new ArrayList<>();
        Map<Question, List<Answer>> hashMap = getQuestionListMap(resultSet);

        for (Map.Entry<Question, List<Answer>> entry : hashMap.entrySet()) {
            Question item = entry.getKey();
            item.setAnswers(entry.getValue());
            questionList.add(item);
        }
        return questionList;
    }

    private Map<Question, List<Answer>> getQuestionListMap(ResultSet resultSet) throws SQLException {
        Map<Question, List<Answer>> hashMap = new HashMap<>();

        while (resultSet.next()) {
            Question question = SQLUtil.getQuestion(resultSet);
            Answer answer = SQLUtil.getAnswer(resultSet);

            if (!hashMap.containsKey(question)) {
                List<Answer> list = new ArrayList<>();
                list.add(answer);
                hashMap.put(question, list);
            } else {
                hashMap.get(question).add(answer);
            }
        }
        return hashMap;
    }

    private boolean isTextUsed(Connection connection, String text) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(GET_QUESTION_BY_TEXT)) {
            st.setString(1, text);
            ResultSet rs = st.executeQuery();
            return rs.first();
        }
    }
}
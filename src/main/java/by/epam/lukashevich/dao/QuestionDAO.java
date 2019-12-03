package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Question;

import java.util.List;

public interface QuestionDAO extends CommonDAO<Question> {

    @Override
    List<Question> findAll() throws DAOException;

    @Override
    Question findById(Integer id) throws DAOException;

    @Override
    boolean add(Question question) throws DAOException;

    @Override
    boolean update(Question question) throws DAOException;

    @Override
    boolean delete(Integer id) throws DAOException;

    int addAndReturnId(Question question) throws DAOException;

    List<Integer> addAnswersList(int questionId, List<Integer> answerIdsList) throws DAOException;

    List<Integer> addQuestionList(List<Question> questions) throws DAOException;

    List<Question> findAllQuestionsForTestId(int testId) throws DAOException;
}

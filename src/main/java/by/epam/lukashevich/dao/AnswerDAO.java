package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.entity.Question;

import java.util.List;

public interface AnswerDAO extends CommonDAO<Answer> {
    @Override
    List<Answer> findAll() throws DAOException;

    @Override
    Answer findById(Integer id) throws DAOException;

    @Override
    boolean add(Answer answer) throws DAOException;

    @Override
    boolean delete(Integer id) throws DAOException;

    int addAndReturnId(Answer answer) throws DAOException;

    List<Answer> findAllAnswersForQuestionId(int questionId) throws DAOException;

    List<Integer> addAnswerList(List<Answer> answers) throws DAOException;
}

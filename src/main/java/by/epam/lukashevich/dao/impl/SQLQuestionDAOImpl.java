package by.epam.lukashevich.dao.impl;

import by.epam.lukashevich.dao.QuestionDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Question;

import java.util.List;

public class SQLQuestionDAOImpl implements QuestionDAO {
    @Override
    public List<Question> findAll() throws DAOException {
        return null;
    }

    @Override
    public Question findById(int id) throws DAOException {
        return null;
    }

    @Override
    public boolean add(Question question) throws DAOException {
        return false;
    }

    @Override
    public boolean update(Question question) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return false;
    }
}

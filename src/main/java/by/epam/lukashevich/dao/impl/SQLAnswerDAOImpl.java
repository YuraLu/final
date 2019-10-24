package by.epam.lukashevich.dao.impl;

import by.epam.lukashevich.dao.AnswerDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Answer;

import java.util.List;

public class SQLAnswerDAOImpl implements AnswerDAO {
    @Override
    public List<Answer> findAll() throws DAOException {
        return null;
    }

    @Override
    public Answer findById(int id) throws DAOException {
        return null;
    }

    @Override
    public boolean add(Answer answer) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return false;
    }
}

package by.epam.trjava.tutorsystem.dao.impl;

import by.epam.trjava.tutorsystem.dao.QuestionDAO;
import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.entity.Question;

import java.util.List;

public class SQLQuestionDAO implements QuestionDAO {
    @Override
    public List<Question> findAll() throws DAOException {
        return null;
    }

    @Override
    public void add(Question newQuestion) throws DAOException {

    }

    @Override
    public void update(int questionId) throws DAOException {

    }

    @Override
    public Question findById(int questionId) throws DAOException {
        return null;
    }

    @Override
    public void delete(int questionId) throws DAOException {

    }
}

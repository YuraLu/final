package by.epam.trjava.tutorsystem.dao.impl;

import by.epam.trjava.tutorsystem.dao.AnswerDAO;
import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.entity.Answer;

import java.util.List;

public class SQLAnswerDAO implements AnswerDAO {
    @Override
    public List<Answer> findAll() throws DAOException {
        return null;
    }

    @Override
    public void add(Answer newAnswer) throws DAOException {

    }

    @Override
    public Answer findById(int answerId) throws DAOException {
        return null;
    }

    @Override
    public void delete(int answerId) throws DAOException {

    }
}

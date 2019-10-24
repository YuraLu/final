package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Answer;

import java.util.List;

public interface AnswerDAO {

    List<Answer> findAll() throws DAOException;

    Answer findById(int id) throws DAOException;

    boolean add(Answer answer) throws DAOException;

    boolean delete(int id) throws DAOException;
}

package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Question;

import java.util.List;

public interface QuestionDAO {

    List<Question> findAll() throws DAOException;

    Question findById(int id) throws DAOException;

    boolean add(Question question) throws DAOException;

    boolean update(Question question) throws DAOException;

    boolean delete(int id) throws DAOException;
}

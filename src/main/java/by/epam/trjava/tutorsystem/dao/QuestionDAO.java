package by.epam.trjava.tutorsystem.dao;

import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.entity.Question;

import java.util.List;

public interface QuestionDAO {

    List<Question> findAll() throws DAOException;

    Question findById(int questionId) throws DAOException;

    boolean add(Question newQuestion) throws DAOException;

    boolean update(Question question) throws DAOException;

    boolean delete(int questionId) throws DAOException;
}

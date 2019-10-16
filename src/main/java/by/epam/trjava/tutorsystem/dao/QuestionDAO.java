package by.epam.trjava.tutorsystem.dao;

import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.entity.Question;

import java.util.List;

public interface QuestionDAO {

    List<Question> findAll() throws DAOException;

    void add(Question newQuestion) throws DAOException;

    void update(int questionId) throws DAOException;

    Question findById(int questionId) throws DAOException;

    void delete(int questionId) throws DAOException;
}

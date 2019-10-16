package by.epam.trjava.tutorsystem.dao;

import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.entity.Answer;

import java.util.List;

public interface AnswerDAO {

    List<Answer> findAll() throws DAOException;

    void add(Answer newAnswer) throws DAOException;

    Answer findById(int answerId) throws DAOException;

    void delete(int answerId) throws DAOException;
}

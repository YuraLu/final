package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Subject;

import java.util.List;

public interface SubjectDAO {
    List<Subject> findAll() throws DAOException;

    Subject findById(int id) throws DAOException;

    boolean add(Subject subject) throws DAOException;

    boolean delete(int id) throws DAOException;
}

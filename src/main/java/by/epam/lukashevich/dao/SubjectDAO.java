package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Subject;

import java.util.List;

public interface SubjectDAO extends CommonDAO<Subject> {

    @Override
    List<Subject> findAll() throws DAOException;

    @Override
    Subject findById(Integer id) throws DAOException;

    @Override
    boolean add(Subject subject) throws DAOException;

    @Override
    boolean delete(Integer id) throws DAOException;
}

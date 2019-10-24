package by.epam.lukashevich.dao.impl;

import by.epam.lukashevich.dao.SubjectDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Subject;

import java.util.List;

public class SQLSubjectDAOImpl implements SubjectDAO {
    @Override
    public List<Subject> findAll() throws DAOException {
        return null;
    }

    @Override
    public Subject findById(int id) throws DAOException {
        return null;
    }

    @Override
    public boolean add(Subject subject) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return false;
    }
}

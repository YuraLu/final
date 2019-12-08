package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Assignment;

import java.util.List;

public interface AssignmentDAO extends CommonDAO<Assignment> {
    @Override
    List<Assignment> findAll() throws DAOException;

    @Override
    Assignment findById(Integer id) throws DAOException;

    @Override
    boolean add(Assignment assignment) throws DAOException;

    @Override
    boolean delete(Integer id) throws DAOException;

    int addAndReturnId(Assignment assignment) throws DAOException;
}
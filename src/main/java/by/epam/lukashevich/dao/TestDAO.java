package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Test;

import java.util.List;

public interface TestDAO extends CommonDAO<Test> {

    @Override
    List<Test> findAll() throws DAOException;

    @Override
    Test findById(Integer id) throws DAOException;

    @Override
    boolean add(Test test) throws DAOException;

    @Override
    boolean update(Test test) throws DAOException;

    @Override
    boolean delete(Integer id) throws DAOException;

}
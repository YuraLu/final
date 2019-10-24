package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Test;

import java.util.List;

public interface TestDAO {
    List<Test> findAll() throws DAOException;

    Test findById(int id) throws DAOException;

    boolean add(Test test) throws DAOException;

    boolean update(Test test) throws DAOException;

    boolean delete(int id) throws DAOException;

}
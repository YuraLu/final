package by.epam.trjava.tutorsystem.dao;

import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.entity.Test;

import java.util.List;

public interface TestDAO {
    List<Test> findAll() throws DAOException;

    Test findById(int testId) throws DAOException;

    boolean add(Test newTest) throws DAOException;

    boolean update(Test test) throws DAOException;

    boolean delete(int testId) throws DAOException;

}
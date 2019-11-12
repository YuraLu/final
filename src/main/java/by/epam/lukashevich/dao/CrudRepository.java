package by.epam.lukashevich.dao;


import by.epam.lukashevich.dao.exception.DAOException;

import java.util.List;

public interface CrudRepository<E, T> {

    List<E> findAll() throws DAOException;

    E findById(T id) throws DAOException;

    boolean add(E e) throws DAOException;

    boolean update(E e) throws DAOException;

    boolean delete(T id) throws DAOException;

}

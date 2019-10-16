package by.epam.trjava.tutorsystem.dao;

import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.entity.Subject;

import java.util.List;

public interface SubjectDAO {
    List<Subject> findAll() throws DAOException;

    Subject findById(int subjectId) throws DAOException;

    boolean add(Subject newSubject) throws DAOException;

    boolean delete(int subjectId) throws DAOException;

}

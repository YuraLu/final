package by.epam.trjava.tutorsystem.service;

import by.epam.trjava.tutorsystem.entity.Test;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import java.util.List;

public interface SubjectService {
    Test findById(int id) throws ServiceException;

    void delete(Test test) throws ServiceException;

    void add(Test test) throws ServiceException;

    List<Test> findAll() throws ServiceException;
}

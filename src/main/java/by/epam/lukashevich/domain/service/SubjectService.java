package by.epam.lukashevich.domain.service;

import by.epam.lukashevich.domain.entity.Subject;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public interface SubjectService {
    List<Subject> findAll() throws ServiceException;

    Subject findById(int id) throws ServiceException;

    void add(Subject subject) throws ServiceException;

    void delete(int id) throws ServiceException;
}
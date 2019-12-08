package by.epam.lukashevich.domain.service;


import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public interface TestService {

    List<Test> findAll() throws ServiceException;

    Test findById(int id) throws ServiceException;

    void add(Test test) throws ServiceException;

    void update(Test test) throws ServiceException;

    void delete(int id) throws ServiceException;
}
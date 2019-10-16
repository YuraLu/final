package by.epam.trjava.tutorsystem.service;

import by.epam.trjava.tutorsystem.entity.Test;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import java.util.List;

public interface TestService {
    Test findTest(String id) throws ServiceException;

    void deleteTest(String id) throws ServiceException;

    void addTest(Test test) throws ServiceException;

    void updateTest(Test test) throws ServiceException;

    List<Test> findAllTests() throws ServiceException;
}




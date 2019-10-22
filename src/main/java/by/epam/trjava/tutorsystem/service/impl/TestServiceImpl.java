package by.epam.trjava.tutorsystem.service.impl;

import by.epam.trjava.tutorsystem.dao.DAOFactory;
import by.epam.trjava.tutorsystem.dao.TestDAO;
import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.entity.Test;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import java.util.List;

public class TestServiceImpl implements by.epam.trjava.tutorsystem.service.TestService {

    private final TestDAO testDAO = DAOFactory.getInstance().getTestDAO();
    public TestServiceImpl() {}

    @Override
    public Test findTest(int id) throws ServiceException {
        try {
            return testDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("Service can't find test", e);
        }
    }

    @Override
    public void deleteTest(int id) throws ServiceException {
        try {
            testDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Service can't delete test", e);
        }
    }

    @Override
    public void addTest(Test test) throws ServiceException {
        try {
            testDAO.add(test);
        } catch (DAOException e) {
            throw new ServiceException("Can't add test", e);
        }
    }

    @Override
    public void updateTest(Test test) throws ServiceException {
        try {
            testDAO.update(test);
        } catch (DAOException e) {
            throw new ServiceException("Can't update test", e);
        }
    }

    @Override
    public List<Test> findAllTests() throws ServiceException {
        try {
            return testDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("No tests", e);
        }
    }
}

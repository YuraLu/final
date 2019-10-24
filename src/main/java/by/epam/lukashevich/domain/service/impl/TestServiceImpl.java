package by.epam.lukashevich.domain.service.impl;

import by.epam.lukashevich.dao.DAOFactory;
import by.epam.lukashevich.dao.TestDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.service.TestService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public class TestServiceImpl implements TestService {

    private final TestDAO testDAO = DAOFactory.getInstance().getTestDAO();

    public TestServiceImpl() {
    }

    @Override
    public List<Test> findAll() throws ServiceException {
        try {
            return testDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("No tests", e);
        }
    }

    @Override
    public Test findById(int id) throws ServiceException {
        try {
            return testDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't find test", e);
        }
    }

    @Override
    public void add(Test test) throws ServiceException {
        try {
            testDAO.add(test);
        } catch (DAOException e) {
            throw new ServiceException("Can't add test", e);
        }
    }


    @Override
    public void update(Test test) throws ServiceException {
        try {
            testDAO.update(test);
        } catch (DAOException e) {
            throw new ServiceException("Can't update test", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            testDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't delete test", e);
        }
    }


}

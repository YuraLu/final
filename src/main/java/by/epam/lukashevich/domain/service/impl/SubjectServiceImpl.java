package by.epam.lukashevich.domain.service.impl;

import by.epam.lukashevich.dao.factory.DAOFactory;
import by.epam.lukashevich.dao.SubjectDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Subject;
import by.epam.lukashevich.domain.service.SubjectService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {

    private final SubjectDAO subjectDAO = DAOFactory.getInstance().getSubjectDAO();

    public SubjectServiceImpl() {
    }

    @Override
    public List<Subject> findAll() throws ServiceException {
        try {
            return subjectDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("No subjects", e);
        }
    }

    @Override
    public Subject findById(int id) throws ServiceException {
        try {
            return subjectDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't find subject", e);
        }
    }

    @Override
    public void add(Subject subject) throws ServiceException {
        try {
            subjectDAO.add(subject);
        } catch (DAOException e) {
            throw new ServiceException("Can't add subject", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            subjectDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't delete subject", e);
        }
    }
}
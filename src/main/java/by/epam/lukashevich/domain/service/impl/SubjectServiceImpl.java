package by.epam.lukashevich.domain.service.impl;

import by.epam.lukashevich.domain.entity.Subject;
import by.epam.lukashevich.domain.service.SubjectService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    @Override
    public List<Subject> findAll() throws ServiceException {
        return null;
    }

    @Override
    public Subject findById(int id) throws ServiceException {
        return null;
    }

    @Override
    public void add(Subject subject) throws ServiceException {

    }

    @Override
    public void delete(Subject subject) throws ServiceException {

    }
}

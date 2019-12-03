package by.epam.lukashevich.domain.service;

import by.epam.lukashevich.domain.entity.Assignment;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public interface AssignmentService {

    List<Assignment> findAll() throws ServiceException;

    Assignment findById(int id) throws ServiceException;

    void add(Assignment assignment) throws ServiceException;

    void delete(int id) throws ServiceException;

    void update(Assignment assignment) throws ServiceException;

    int addAndReturnId(Assignment assignment) throws ServiceException;

    int getAssignmentScore(Assignment assignment);

    void save(Assignment assignment) throws ServiceException;
}

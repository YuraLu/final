package by.epam.trjava.tutorsystem.service;

import by.epam.trjava.tutorsystem.entity.Question;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import java.util.List;

public interface QuestionService {

    List<Question> findAll() throws ServiceException;

    void add(Question newQuestion) throws ServiceException;

    void update(int questionId) throws ServiceException;

    Question findById(int questionId) throws ServiceException;

    void delete(int questionId) throws ServiceException;
}

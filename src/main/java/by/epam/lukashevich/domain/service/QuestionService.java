package by.epam.lukashevich.domain.service;

import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public interface QuestionService {

    List<Question> findAll() throws ServiceException;

    Question findById(int questionId) throws ServiceException;

    void add(Question newQuestion) throws ServiceException;

    void update(int questionId) throws ServiceException;

    void delete(int questionId) throws ServiceException;
}

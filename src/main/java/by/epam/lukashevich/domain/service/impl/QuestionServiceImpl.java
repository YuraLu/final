package by.epam.lukashevich.domain.service.impl;

import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.service.QuestionService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    @Override
    public List<Question> findAll() throws ServiceException {
        return null;
    }

    @Override
    public Question findById(int questionId) throws ServiceException {
        return null;
    }

    @Override
    public void add(Question newQuestion) throws ServiceException {

    }

    @Override
    public void update(int questionId) throws ServiceException {

    }

    @Override
    public void delete(int questionId) throws ServiceException {

    }
}

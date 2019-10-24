package by.epam.lukashevich.domain.service.impl;

import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.service.AnswerService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {
    @Override
    public List<Answer> findAll() throws ServiceException {
        return null;
    }

    @Override
    public Answer findById(int answerId) throws ServiceException {
        return null;
    }

    @Override
    public void add(Answer newAnswer) throws ServiceException {

    }

    @Override
    public void delete(int answerId) throws ServiceException {

    }
}

package by.epam.trjava.tutorsystem.service.impl;

import by.epam.trjava.tutorsystem.entity.Answer;
import by.epam.trjava.tutorsystem.service.AnswerService;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {
    @Override
    public List<Answer> findAll() throws ServiceException {
        return null;
    }

    @Override
    public void add(Answer newAnswer) throws ServiceException {

    }

    @Override
    public Answer findById(int answerId) throws ServiceException {
        return null;
    }

    @Override
    public void delete(int answerId) throws ServiceException {

    }
}

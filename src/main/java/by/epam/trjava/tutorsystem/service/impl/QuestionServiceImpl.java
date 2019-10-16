package by.epam.trjava.tutorsystem.service.impl;

import by.epam.trjava.tutorsystem.entity.Question;
import by.epam.trjava.tutorsystem.service.QuestionService;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    @Override
    public List<Question> findAll() throws ServiceException {
        return null;
    }

    @Override
    public void add(Question newQuestion) throws ServiceException {

    }

    @Override
    public void update(int questionId) throws ServiceException {

    }

    @Override
    public Question findById(int questionId) throws ServiceException {
        return null;
    }

    @Override
    public void delete(int questionId) throws ServiceException {

    }
}

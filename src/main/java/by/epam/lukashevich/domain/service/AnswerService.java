package by.epam.lukashevich.domain.service;

import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public interface AnswerService {

    List<Answer> findAll() throws ServiceException;

    Answer findById(int answerId) throws ServiceException;

    void add(Answer newAnswer) throws ServiceException;

    void delete(int answerId) throws ServiceException;
}

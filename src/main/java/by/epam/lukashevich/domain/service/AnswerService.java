package by.epam.lukashevich.domain.service;

import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public interface AnswerService {

    List<Answer> findAll() throws ServiceException;

    Answer findById(int id) throws ServiceException;

    void add(Answer answer) throws ServiceException;

    void delete(int id) throws ServiceException;

    int addAndReturnId(Answer answer) throws ServiceException;

    List<Integer> addAnswerList(List<Answer> answers) throws ServiceException;

    List<Answer> findAllAnswersForQuestionId(int questionId)throws ServiceException;
}
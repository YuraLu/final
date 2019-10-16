package by.epam.trjava.tutorsystem.service;

import by.epam.trjava.tutorsystem.entity.Answer;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import java.util.List;

public interface AnswerService {

    List<Answer> findAll() throws ServiceException;

    void add(Answer newAnswer) throws ServiceException;

    Answer findById(int answerId) throws ServiceException;

    void delete(int answerId) throws ServiceException;
}

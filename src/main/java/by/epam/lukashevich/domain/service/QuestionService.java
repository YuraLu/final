package by.epam.lukashevich.domain.service;

import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public interface QuestionService {

    List<Question> findAll() throws ServiceException;

    Question findById(int id) throws ServiceException;

    void add(Question question) throws ServiceException;

    void update(Question question) throws ServiceException;

    void delete(int id) throws ServiceException;

    List<Integer> addQuestionList(List<Question> questions) throws ServiceException;

    List<Question> findAllQuestionsForTestId(int testId)throws ServiceException;

    void addQuestionToTest(Question question, int testId) throws ServiceException;
}

package by.epam.lukashevich.domain.service.impl;

import by.epam.lukashevich.dao.TestDAO;
import by.epam.lukashevich.dao.factory.DAOFactory;
import by.epam.lukashevich.dao.QuestionDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.service.QuestionService;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDAO questionDAO = DAOFactory.getInstance().getQuestionDAO();
    private final TestDAO testDAO = DAOFactory.getInstance().getTestDAO();

    public QuestionServiceImpl() {
    }

    @Override
    public List<Question> findAll() throws ServiceException {
        try {
            return questionDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("No questions", e);
        }
    }

    @Override
    public Question findById(int id) throws ServiceException {
        try {
            return questionDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't find question", e);
        }
    }

    @Override
    public void add(Question question) throws ServiceException {
        try {
            int questionId = questionDAO.addAndReturnId(question);
            List<Integer> answerIdsList = ServiceProvider.getInstance().getAnswerService().addAnswerList(question.getAnswers());
            questionDAO.addAnswersList(questionId, answerIdsList);
        } catch (DAOException e) {
            throw new ServiceException("Can't add question", e);
        }
    }

    @Override
    public void update(Question question) throws ServiceException {
        try {
            questionDAO.update(question);
        } catch (DAOException e) {
            throw new ServiceException("Can't update question", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            questionDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't delete question", e);
        }
    }

    @Override
    public List<Integer> addQuestionList(List<Question> questions) throws ServiceException {
        try {
            return questionDAO.addQuestionList(questions);
        } catch (DAOException e) {
            throw new ServiceException("Can't add questions list", e);
        }
    }


    @Override
    public List<Question> findAllQuestionsForTestId(int testId) throws ServiceException {
        try {
            return questionDAO.findAllQuestionsForTestId(testId);
        } catch (DAOException e) {
            throw new ServiceException("Can't find all question for testId", e);
        }
    }

    @Override
    public void addQuestionToTest(Question question, int testId) throws ServiceException {
            try {
            int questionId = questionDAO.addAndReturnId(question);
            List<Integer> answerIdsList = ServiceProvider.getInstance().getAnswerService().addAnswerList(question.getAnswers());
            questionDAO.addAnswersList(questionId, answerIdsList);

            List<Integer> questionIdsList = new ArrayList<>();
            questionIdsList.add(questionId);
            testDAO.addQuestionsList(testId, questionIdsList);
        } catch (DAOException e) {
            throw new ServiceException("Can't add Question to Test", e);
        }
    }
}

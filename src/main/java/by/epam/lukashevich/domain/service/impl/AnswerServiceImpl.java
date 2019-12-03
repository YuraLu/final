package by.epam.lukashevich.domain.service.impl;

import by.epam.lukashevich.dao.factory.DAOFactory;
import by.epam.lukashevich.dao.AnswerDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.service.AnswerService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {
    private final AnswerDAO answerDAO = DAOFactory.getInstance().getAnswerDAO();

    public AnswerServiceImpl() {
    }

    @Override
    public List<Answer> findAll() throws ServiceException {
        try {
            return answerDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("No answers", e);
        }
    }

    @Override
    public Answer findById(int id) throws ServiceException {
        try {
            return answerDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't find answer", e);
        }
    }

    @Override
    public void add(Answer answer) throws ServiceException {
        try {
            answerDAO.add(answer);
        } catch (DAOException e) {
            throw new ServiceException("Can't add answer", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            answerDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't delete answer", e);
        }
    }


    @Override
    public int addAndReturnId(Answer answer) throws ServiceException {
        try {
            return answerDAO.addAndReturnId(answer);
        } catch (DAOException e) {
            throw new ServiceException("Can't add answer", e);
        }
    }

    @Override
    public List<Integer> addAnswerList(List<Answer> answers) throws ServiceException {
        try {
            return answerDAO.addAnswerList(answers);
        } catch (DAOException e) {
            throw new ServiceException("Can't add answer", e);
        }
    }


    @Override
    public List<Answer> findAllAnswersForQuestionId(int questionId)throws ServiceException {
        try {
            return answerDAO.findAllAnswersForQuestionId(questionId);
        } catch (DAOException e) {
            throw new ServiceException("Can't find all answers for questionId", e);
        }
    }
}

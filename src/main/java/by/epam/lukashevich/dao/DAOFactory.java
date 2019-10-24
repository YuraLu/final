package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.impl.*;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    private final AnswerDAO answerDAO = new SQLAnswerDAOImpl();
    private final QuestionDAO questionDAO = new SQLQuestionDAOImpl();
    private final ReplyDAO replyDAO = new SQLReplyDAOImpl();
    private final SubjectDAO subjectDAO = new SQLSubjectDAOImpl();
    private final TestDAO testDAO = new SQLTestDAOImpl();
    private final UserDAO userDAO = new SQLUserDAOImpl();

    public AnswerDAO getAnswerDAO() {
        return answerDAO;
    }

    public QuestionDAO getQuestionDAO() {
        return questionDAO;
    }

    public ReplyDAO getReplyDAO() {
        return replyDAO;
    }

    public SubjectDAO getSubjectDAO() {
        return subjectDAO;
    }

    public TestDAO getTestDAO() {
        return testDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}

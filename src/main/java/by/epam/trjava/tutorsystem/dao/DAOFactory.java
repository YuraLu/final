package by.epam.trjava.tutorsystem.dao;

import by.epam.trjava.tutorsystem.dao.impl.*;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final AnswerDAO answerDAO = new SQLAnswerDAO();
    private final QuestionDAO questionDAO = new SQLQuestionDAO();
    private final ReplyDAO replyDAO = new SQLReplyDAO();
    private final RoleDAO roleDAO = new SQLRoleDAO();
    private final SubjectDAO subjectDAO = new SQLSubjectDAO();
    private final TestDAO testDAO = new SQLTestDAO();
    private final UserDAO userDAO = new SQLUserDAO();

    private DAOFactory() {
    }

// private static volatile DAOFactory instance;
//   public static DAOFactory getInstance() {
//       if (instance == null) {
//           instance = new DAOFactory();
//       }
//       return instance;
//   }

    public static DAOFactory getInstance() {
        return instance;
    }

    public AnswerDAO getAnswerDAO() {
        return answerDAO;
    }

    public QuestionDAO getQuestionDAO() {
        return questionDAO;
    }

    public ReplyDAO getReplyDAO() {
        return replyDAO;
    }

    public RoleDAO getRoleDAO() {
        return roleDAO;
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

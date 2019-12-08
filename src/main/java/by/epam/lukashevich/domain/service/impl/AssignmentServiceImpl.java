package by.epam.lukashevich.domain.service.impl;

import by.epam.lukashevich.dao.AssignmentDAO;
import by.epam.lukashevich.dao.ReplyDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.dao.factory.DAOFactory;
import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.entity.Assignment;
import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.entity.Reply;
import by.epam.lukashevich.domain.service.AssignmentService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentDAO assignmentDAO = DAOFactory.getInstance().getAssignmentDAO();
    private final ReplyDAO replyDAO = DAOFactory.getInstance().getReplyDAO();


    @Override
    public List<Assignment> findAll() throws ServiceException {
        try {
            return assignmentDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("No assignments", e);
        }
    }

    @Override
    public Assignment findById(int id) throws ServiceException {
        try {
            return assignmentDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't find assignment", e);
        }
    }

    @Override
    public void add(Assignment assignment) throws ServiceException {
        try {
            assignmentDAO.add(assignment);
        } catch (DAOException e) {
            throw new ServiceException("Can't add assignment", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {

    }

    @Override
    public void update(Assignment assignment) throws ServiceException {
        try {
            assignmentDAO.update(assignment);
        } catch (DAOException e) {
            throw new ServiceException("Can't update assignment", e);
        }
    }

    @Override
    public int addAndReturnId(Assignment assignment) throws ServiceException {
        try {
            return assignmentDAO.addAndReturnId(assignment);
        } catch (DAOException e) {
            throw new ServiceException("Can't add assignment", e);
        }
    }

    @Override
    public int getAssignmentScore(Assignment assignment) {

        Map<Question, List<Answer>> questionAnswerListMap = getOriginalQuestionListMap(assignment);

        Map<Question, List<Answer>> questionAnswerListMapFromReply = getReplyQuestionListMap(assignment);

        return getScore(questionAnswerListMap, questionAnswerListMapFromReply);
    }

    @Override
    public void save(Assignment assignment) throws ServiceException {

        int assignmentId = assignment.getId();
        List<Reply> replyList = assignment.getReplies();

        try {
            for (Reply reply : replyList) {
                reply.setAssignmentId(assignmentId);
                replyDAO.add(reply);
            }
        } catch (DAOException e) {
            throw new ServiceException("Can't add replies for assignment", e);
        }
    }

    private int getScore(Map<Question, List<Answer>> questionAnswerListMap, Map<Question, List<Answer>> questionAnswerListMapFromReply) {
        int score = 0;
        for (Map.Entry<Question, List<Answer>> item : questionAnswerListMap.entrySet()) {
            int questionId = item.getKey().getId();

            for (Map.Entry<Question, List<Answer>> itemFromReply : questionAnswerListMapFromReply.entrySet()) {
                int questionIdFromReply = itemFromReply.getKey().getId();

                if (questionId == questionIdFromReply) {
                    if (compareAnswers(item.getValue(), itemFromReply.getValue())) {
                        score++;
                    }
                }
            }
        }
        return score;
    }

    private Map<Question, List<Answer>> getReplyQuestionListMap(Assignment assignment) {
        List<Reply> replyList = assignment.getReplies();
        Map<Question, List<Answer>> questionAnswerListMapFromReply = new HashMap<>();

        for (Reply reply : replyList) {
            Question questionFromReply = reply.getQuestion();
            List<Answer> answerListFromReply = reply.getAnswers();
            questionAnswerListMapFromReply.put(questionFromReply, answerListFromReply);
        }
        return questionAnswerListMapFromReply;
    }

    private Map<Question, List<Answer>> getOriginalQuestionListMap(Assignment assignment) {
        List<Question> questionList = assignment.getTest().getQuestions();
        Map<Question, List<Answer>> questionAnswerListMap = new HashMap<>();

        for (Question item : questionList) {
            List<Answer> answerList = item.getAnswers();
            questionAnswerListMap.put(item, answerList);
        }
        return questionAnswerListMap;
    }

    private boolean compareAnswers(List<Answer> questionAnswerList, List<Answer> questionAnswerListFromReply) {
        int result = 0;
        int correctOriginalAnswers = 0;

        for (Answer originalAnswer : questionAnswerList) {
            if (originalAnswer.getIsCorrect()) {
                correctOriginalAnswers++;
            }
        }

        for (Answer originalAnswer : questionAnswerList) {
            for (Answer replyAnswer : questionAnswerListFromReply) {
                if (originalAnswer.getId() == replyAnswer.getId()) {
                    if (originalAnswer.getIsCorrect() == replyAnswer.getIsCorrect()) {
                        result++;
                    }
                }
            }
        }
        return result == correctOriginalAnswers;
    }
}

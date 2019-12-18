package by.epam.lukashevich.domain.command.impl.test;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.command.impl.util.CheckMessage;
import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.entity.Subject;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.service.AnswerService;
import by.epam.lukashevich.domain.service.QuestionService;
import by.epam.lukashevich.domain.service.SubjectService;
import by.epam.lukashevich.domain.service.TestService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.service.provider.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.config.JSPPage.TEST_WORK_PAGE;

public class CommandViewTestWorkPage implements Command {

    private TestService service = ServiceProvider.getInstance().getTestService();
    private SubjectService subjectService = ServiceProvider.getInstance().getSubjectService();
    private QuestionService questionService = ServiceProvider.getInstance().getQuestionService();
    private AnswerService answerService = ServiceProvider.getInstance().getAnswerService();

    public void setService(TestService service) {
        this.service = service;
    }

    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();

        final int testId = Integer.parseInt(request.getParameter(TEST_ID));

        try {

            Test test = service.findById(testId);
            List<Subject> subjectList = subjectService.findAll();
            List<Question> questionList = questionService.findAllQuestionsForTestId(testId);

            for (Question question : questionList) {
                List<Answer> answerList = answerService.findAllAnswersForQuestionId(question.getId());
                question.setAnswers(answerList);
            }

            test.setQuestions(questionList);

            request.setAttribute(TEST_OBJECT, test);
            request.setAttribute(SUBJECT_LIST, subjectList);

            CheckMessage.checkMessageToJsp(session, request, MESSAGE_ERRORS_TO_JSP);

        } catch (ServiceException e) {
            throw new CommandException("Can't get list of tests in execute() CommandViewTestTable", e);
        }

        return TEST_WORK_PAGE;
    }
}
package by.epam.lukashevich.domain.command.impl.test;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.command.impl.util.CheckMessage;
import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.entity.Subject;
import by.epam.lukashevich.domain.entity.Test;
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
import static by.epam.lukashevich.domain.config.JSPPage.TEST_TABLE_PAGE;

/**
 * Forwards to test table page
 *
 * @author Lukashevich_Y_A
 */
public class CommandViewTestTable implements Command {

    private TestService service = ServiceProvider.getInstance().getTestService();
    private SubjectService subjectService = ServiceProvider.getInstance().getSubjectService();
    private QuestionService questionService = ServiceProvider.getInstance().getQuestionService();

    public void setService(TestService service) {
        this.service = service;
    }

    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();

        try {
            List<Test> list = service.findAll();
            request.setAttribute(TEST_LIST, list);

            List<Subject> subjectList = subjectService.findAll();
            List<Question> questionList = questionService.findAll();

            request.setAttribute(SUBJECT_LIST, subjectList);
            request.setAttribute(QUESTION_LIST, questionList);

            CheckMessage.checkMessageToJsp(session, request, MESSAGE_ERRORS_TO_JSP);

        } catch (ServiceException e) {
            throw new CommandException("Can't get list of tests in execute() CommandViewTestTable", e);
        }
        return TEST_TABLE_PAGE;
    }
}
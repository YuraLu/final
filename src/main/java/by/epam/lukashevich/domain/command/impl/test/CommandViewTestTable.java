package by.epam.lukashevich.domain.command.impl.test;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
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
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final TestService service = ServiceProvider.getInstance().getTestService();
        final SubjectService subjectService = ServiceProvider.getInstance().getSubjectService();
        final QuestionService questionService = ServiceProvider.getInstance().getQuestionService();

        try {
            List<Test> list = service.findAll();
            request.setAttribute(TEST_LIST, list);

            List<Subject> subjectList = subjectService.findAll();
            List<Question> questionList = questionService.findAll();

            request.setAttribute(SUBJECT_LIST, subjectList);
            request.setAttribute(QUESTION_LIST, questionList);

        } catch (ServiceException e) {
            throw new CommandException("Can't get list of tests in execute() CommandViewTestTable", e);
        }
        return TEST_TABLE_PAGE;
    }
}
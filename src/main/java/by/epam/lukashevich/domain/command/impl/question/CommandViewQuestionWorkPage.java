package by.epam.lukashevich.domain.command.impl.question;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.service.QuestionService;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.config.JSPPages.QUESTION_WORK_PAGE;

public class CommandViewQuestionWorkPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final QuestionService questionService = ServiceProvider.getInstance().getQuestionService();
        final int questionId = Integer.parseInt(request.getParameter(QUESTION_ID));
//        final int testId = Integer.parseInt(request.getParameter(TEST_ID));


        try {
            Question question = questionService.findById(questionId);
            request.setAttribute(QUESTION_OBJECT, question);
//            request.setAttribute(TEST_ID, testId);

        } catch (ServiceException e) {
            throw new CommandException("Can't get list of questions in execute() CommandViewQuestionWorkPage", e);
        }
        return QUESTION_WORK_PAGE;
    }
}
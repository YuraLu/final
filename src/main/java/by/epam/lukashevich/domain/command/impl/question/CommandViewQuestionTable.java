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
import java.util.List;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.QUESTION_LIST;
import static by.epam.lukashevich.domain.util.config.JSPPages.QUESTION_TABLE_PAGE;

public class CommandViewQuestionTable implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final QuestionService questionService = ServiceProvider.getInstance().getQuestionService();

        try {
            List<Question> list = questionService.findAll();
            request.setAttribute(QUESTION_LIST, list);

        } catch (ServiceException e) {
            throw new CommandException("Can't get list of questions in execute() CommandViewQuestionTable", e);
        }
        return QUESTION_TABLE_PAGE;
    }
}
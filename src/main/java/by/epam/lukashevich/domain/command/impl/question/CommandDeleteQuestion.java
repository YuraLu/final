package by.epam.lukashevich.domain.command.impl.question;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.service.QuestionService;
import by.epam.lukashevich.domain.service.provider.ServiceProvider;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.QUESTION_ID;
import static by.epam.lukashevich.domain.config.BeanFieldJsp.REDIRECT_COMMAND;
import static by.epam.lukashevich.domain.config.JSPActionCommand.VIEW_QUESTION_TABLE_COMMAND;
import static by.epam.lukashevich.domain.config.JSPPages.QUESTION_TABLE_PAGE;

public class CommandDeleteQuestion implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        final QuestionService questionService = ServiceProvider.getInstance().getQuestionService();
        final int id = Integer.parseInt(request.getParameter(QUESTION_ID));

        try {
            questionService.delete(id);
        } catch (ServiceException e) {
            throw new CommandException("Can't delete question in execute() CommandDeleteQuestion", e);
        }

        session.setAttribute(REDIRECT_COMMAND, VIEW_QUESTION_TABLE_COMMAND);
        return QUESTION_TABLE_PAGE;
    }
}
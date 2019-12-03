package by.epam.lukashevich.domain.command.impl.question;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.TEST_ID;
import static by.epam.lukashevich.domain.util.config.JSPPages.QUESTION_WORK_PAGE;

public class CommandViewQuestionAdd implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final int testId = Integer.parseInt(request.getParameter(TEST_ID));
        request.setAttribute(TEST_ID, testId);

        return QUESTION_WORK_PAGE;
    }
}
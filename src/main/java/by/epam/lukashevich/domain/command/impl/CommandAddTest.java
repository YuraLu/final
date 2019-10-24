package by.epam.lukashevich.domain.command.impl;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.TestService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.util.TestBuilder;
import by.epam.lukashevich.domain.util.impl.TestBuilderImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.config.JSPActionCommand.VIEW_TEST_TABLE;
import static by.epam.lukashevich.domain.util.config.JSPPages.INDEX;
import static by.epam.lukashevich.domain.util.config.JSPPages.TEST_TABLE_PAGE;

public class CommandAddTest implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        final TestService testService = ServiceProvider.getInstance().getTestService();

        final String title = request.getParameter(TEST_TITLE);
        final String description = request.getParameter(TEST_DESCRIPTION);
        final int subjectId = Integer.parseInt(request.getParameter(TEST_SUBJECT));
        final int authorId = Integer.parseInt(request.getParameter(TEST_AUTHOR));

        try {
            TestBuilder builder = new TestBuilderImpl();
            Test test = builder.withTitle(title)
                        .withDescription(description)
                        .withSubjectId(subjectId)
                        .withAuthorId(authorId)
                        .build();
            testService.add(test);
            session.setAttribute(REDIRECT_COMMAND, VIEW_TEST_TABLE);
            response.sendRedirect(TEST_TABLE_PAGE);
        } catch (ServiceException e) {
            response.sendRedirect(INDEX);
            throw new CommandException("Can't add test in execute() CommandAddTest", e);
        }
    }
}

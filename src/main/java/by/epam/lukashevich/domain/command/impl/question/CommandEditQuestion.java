package by.epam.lukashevich.domain.command.impl.question;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.Subject;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.SubjectService;
import by.epam.lukashevich.domain.service.TestService;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.util.builder.TestBuilder;
import by.epam.lukashevich.domain.util.builder.impl.TestBuilderImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.config.JSPActionCommand.VIEW_TEST_TABLE;
import static by.epam.lukashevich.domain.util.config.JSPPages.INDEX;
import static by.epam.lukashevich.domain.util.config.JSPPages.TEST_TABLE_PAGE;

public class CommandEditQuestion implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        final TestService testService = ServiceProvider.getInstance().getTestService();

        final int id = Integer.parseInt(request.getParameter(TEST_ID));
        final String title = request.getParameter(TEST_TITLE);
        final String description = request.getParameter(TEST_DESCRIPTION);
        final int subjectId = Integer.parseInt(request.getParameter(TEST_SUBJECT_ID));
        final int authorId = Integer.parseInt(request.getParameter(TEST_AUTHOR_ID));

        final SubjectService subjectService = ServiceProvider.getInstance().getSubjectService();
        final UserService userService = ServiceProvider.getInstance().getUserService();

        try {
            Subject subject = subjectService.findById(subjectId);
            User author = userService.findById(authorId);

            TestBuilder builder = new TestBuilderImpl(id);
            Test test = builder.withTitle(title)
                        .withDescription(description)
                        .withSubject(subject)
                        .withAuthor(author)
                        .build();
            testService.update(test);
            session.setAttribute(REDIRECT_COMMAND, VIEW_TEST_TABLE);
            response.sendRedirect(TEST_TABLE_PAGE);
        } catch (ServiceException e) {
            response.sendRedirect(INDEX);
            throw new CommandException("Can't edit test in execute() CommandEditTest", e);
        }
    }
}

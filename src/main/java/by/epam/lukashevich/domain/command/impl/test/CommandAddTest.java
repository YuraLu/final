package by.epam.lukashevich.domain.command.impl.test;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.Subject;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.SubjectService;
import by.epam.lukashevich.domain.service.TestService;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.service.provider.ServiceProvider;
import by.epam.lukashevich.domain.util.builder.impl.TestBuilderImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.config.JSPActionCommand.VIEW_TEST_TABLE_COMMAND;
import static by.epam.lukashevich.domain.config.JSPPage.INDEX_PAGE;
import static by.epam.lukashevich.domain.config.JSPPage.TEST_TABLE_PAGE;
import static by.epam.lukashevich.domain.config.Message.MESSAGE_DELETE_SUBJECT_ERROR;

/**
 * Adds test
 *
 * @author Lukashevich_Y_A
 */
public class CommandAddTest implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final SubjectService subjectService = ServiceProvider.getInstance().getSubjectService();
        final UserService userService = ServiceProvider.getInstance().getUserService();

        final HttpSession session = request.getSession();
        final TestService service = ServiceProvider.getInstance().getTestService();

        final String title = request.getParameter(TEST_TITLE);
        final String description = request.getParameter(TEST_DESCRIPTION);
        final int subjectId = Integer.parseInt(request.getParameter(TEST_SUBJECT_ID));
        final int authorId = Integer.parseInt(request.getParameter(TEST_AUTHOR_ID));

        if (title.isEmpty()) {
            throw new CommandException("No data in title field during Test add in execute() CommandAddQuestion");
        }

        try {
            Subject subject = subjectService.findById(subjectId);
            User author = userService.findById(authorId);

            Test test = new TestBuilderImpl().withTitle(title)
                    .withDescription(description)
                    .withSubject(subject)
                    .withAuthor(author)
                    .build();
            service.add(test);
            session.setAttribute(MESSAGE_TO_JSP, MESSAGE_DELETE_SUBJECT_ERROR);

        } catch (ServiceException e) {
            response.sendRedirect(INDEX_PAGE);
            throw new CommandException("Can't add test in execute() CommandAddTest", e);
        }
        session.setAttribute(REDIRECT_COMMAND, VIEW_TEST_TABLE_COMMAND);
        return TEST_TABLE_PAGE;
    }
}
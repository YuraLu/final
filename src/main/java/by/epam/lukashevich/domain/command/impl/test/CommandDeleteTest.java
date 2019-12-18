package by.epam.lukashevich.domain.command.impl.test;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.service.TestService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.service.provider.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.config.JSPActionCommand.VIEW_TEST_TABLE_COMMAND;
import static by.epam.lukashevich.domain.config.JSPPage.TEST_TABLE_PAGE;
import static by.epam.lukashevich.domain.config.Message.MESSAGE_DELETE_TEST_ERROR;

/**
 * Deletes test
 *
 * @author Lukashevich_Y_A
 */
public class CommandDeleteTest implements Command {

    private TestService testService = ServiceProvider.getInstance().getTestService();

    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();

        final int id = Integer.parseInt(request.getParameter(TEST_ID));

        try {
            testService.delete(id);
        } catch (ServiceException e) {
            session.setAttribute(MESSAGE_ERRORS_TO_JSP, MESSAGE_DELETE_TEST_ERROR);
        }

        session.setAttribute(REDIRECT_COMMAND, VIEW_TEST_TABLE_COMMAND);
        return TEST_TABLE_PAGE;
    }
}
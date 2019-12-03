package by.epam.lukashevich.domain.command.impl.test;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.TestService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.REDIRECT_COMMAND;
import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.TEST_ID;
import static by.epam.lukashevich.domain.util.config.JSPActionCommand.VIEW_TEST_TABLE_COMMAND;
import static by.epam.lukashevich.domain.util.config.JSPPages.TEST_TABLE_PAGE;

public class CommandDeleteTest implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        final TestService service = ServiceProvider.getInstance().getTestService();
        final int id = Integer.parseInt(request.getParameter(TEST_ID));

        try {
            service.delete(id);

        } catch (ServiceException e) {
            throw new CommandException("Can't delete test in execute() CommandDeleteTest", e);
        }

        session.setAttribute(REDIRECT_COMMAND, VIEW_TEST_TABLE_COMMAND);
        return TEST_TABLE_PAGE;
    }
}
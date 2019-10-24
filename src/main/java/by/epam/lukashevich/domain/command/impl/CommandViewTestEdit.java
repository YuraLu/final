package by.epam.lukashevich.domain.command.impl;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.TestService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.config.JSPPages.*;

public class CommandViewTestEdit implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        final TestService service = ServiceProvider.getInstance().getTestService();
        final int id = Integer.parseInt(request.getParameter(TEST_FOR_ACTION));

        try {
            Test test = service.findById(id);
            request.setAttribute(TEST_OBJECT, test);
            request.getRequestDispatcher(TEST_EDIT_PAGE).forward(request, response);
        } catch (ServiceException e) {
            throw new CommandException("Can't get list of tests in execute() CommandViewTestTable", e);
        }
    }
}

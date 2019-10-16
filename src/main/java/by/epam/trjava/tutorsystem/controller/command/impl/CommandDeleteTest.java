package by.epam.trjava.tutorsystem.controller.command.impl;

import by.epam.trjava.tutorsystem.controller.command.Command;
import by.epam.trjava.tutorsystem.controller.command.util.CreatorFullURL;
import by.epam.trjava.tutorsystem.service.ServiceProvider;
import by.epam.trjava.tutorsystem.service.TestService;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.trjava.tutorsystem.controller.command.BeanFieldJsp.*;

public class CommandDeleteTest implements Command {

    private static final String TARGET_PAGE = "pages?command=showAllTests";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        String url = CreatorFullURL.create(request);

        String id = request.getParameter(TEST_ID);
        TestService testService = ServiceProvider.getInstance().getTestService();

        try {
            testService.deleteTest(id);
        } catch (ServiceException e) {
            response.sendError(ERROR_NUMBER_500);
            throw new ServletException("Can't delete Test in execute() CommandDeleteTest", e);
        }
        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
        response.sendRedirect(TARGET_PAGE);
    }
}


package by.epam.trjava.tutorsystem.controller.command.impl;

import by.epam.trjava.tutorsystem.controller.command.Command;
import by.epam.trjava.tutorsystem.controller.command.util.CreatorFullURL;
import by.epam.trjava.tutorsystem.entity.Test;
import by.epam.trjava.tutorsystem.service.ServiceProvider;
import by.epam.trjava.tutorsystem.service.TestService;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.trjava.tutorsystem.controller.command.BeanFieldJsp.*;

public class CommandTestInfo implements Command {

    private static final String REDIRECT_PAGE_URL = "pages?command=goToShowTestInfoPageCommand";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String url = CreatorFullURL.create(request);

        int id = Integer.parseInt(request.getParameter(TEST_ID));
        TestService testService = ServiceProvider.getInstance().getTestService();
        Test test = null;

        try {
            test = testService.findTest(id);
        } catch (ServiceException e) {
            response.sendError(ERROR_NUMBER_500);
            throw new ServletException("Can't find Test in execute() CommandTestInfo",e);
        }
        session.setAttribute(TEST_INFO, test);
        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
        response.sendRedirect(REDIRECT_PAGE_URL);
    }
}

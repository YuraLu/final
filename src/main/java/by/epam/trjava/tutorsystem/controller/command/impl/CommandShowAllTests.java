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
import java.util.List;

import static by.epam.trjava.tutorsystem.controller.command.BeanFieldJsp.*;

public class CommandShowAllTests implements Command {

    private static final String REDIRECT_PAGE_URL = "pages?command=goToShowAllTestsPageCommand";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        String url = CreatorFullURL.create(request);

        List<Test> list;
        TestService service = ServiceProvider.getInstance().getTestService();

        try {
            list = service.findAllTests();
        } catch (ServiceException e) {
            response.sendError(ERROR_NUMBER_500);
            throw new ServletException("Can't find show any tests in execute() CommandShowAllTests", e);
        }
        session.setAttribute(TEST_LIST, list);
        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
        response.sendRedirect(REDIRECT_PAGE_URL);
    }
}
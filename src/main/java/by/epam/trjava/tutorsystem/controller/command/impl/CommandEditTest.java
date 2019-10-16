package by.epam.trjava.tutorsystem.controller.command.impl;

import by.epam.trjava.tutorsystem.controller.command.Command;
import by.epam.trjava.tutorsystem.controller.command.util.CreatorFullURL;
import by.epam.trjava.tutorsystem.entity.Test;
import by.epam.trjava.tutorsystem.service.ServiceProvider;
import by.epam.trjava.tutorsystem.service.TestService;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.trjava.tutorsystem.controller.command.BeanFieldJsp.*;

public class CommandEditTest implements Command {
    private static final String TARGET_PAGE = "pages?command=showAllTests";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter(TEST_ID));
        String title = request.getParameter(TEST_TITLE);
        String description = request.getParameter(TEST_DESCRIPTION);
        int subjectId = Integer.parseInt(request.getParameter(TEST_SUBJECT));
        int authorId = Integer.parseInt(request.getParameter(TEST_AUTHOR));

        Test test = new Test();
        test.setId(id);
        test.setTitle(title);
        test.setDescription(description);
        test.setSubjectId(subjectId);
        test.setAuthorId(authorId);

        TestService service = ServiceProvider.getInstance().getTestService();

        try {
            service.updateTest(test);
        } catch (ServiceException e) {
            throw new ServletException("Can't find and edit test in execute() CommandEditTest", e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(TARGET_PAGE);
        dispatcher.forward(request, response);
    }
}

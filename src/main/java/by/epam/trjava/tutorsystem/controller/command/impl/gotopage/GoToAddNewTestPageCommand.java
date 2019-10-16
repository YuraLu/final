package by.epam.trjava.tutorsystem.controller.command.impl.gotopage;

import by.epam.trjava.tutorsystem.controller.command.Command;
import by.epam.trjava.tutorsystem.controller.command.util.CreatorFullURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.trjava.tutorsystem.controller.command.BeanFieldJsp.PARAMETER_PREVIOUS_REQUEST;

public class GoToAddNewTestPageCommand implements Command {

    private static final String TARGET_PAGE = "/WEB-INF/jsp/addNewTest.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = CreatorFullURL.create(request);
        HttpSession session = request.getSession(true);
        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(TARGET_PAGE);
        dispatcher.forward(request, response);
    }
}

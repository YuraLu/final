package by.epam.trjava.tutorsystem.controller.command.impl;

import by.epam.trjava.tutorsystem.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class CommandLogOut implements Command {

    private static final String TARGET_PAGE = "pages?command=showAllTests";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        HttpSession session = request.getSession(true);
//        session.removeAttribute(USER_OBJECT);
//
//        String url = CreatorFullURL.create(request);
//        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher(TARGET_PAGE);
//        dispatcher.forward(request, response);

        //invalidate the session if exists
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(TARGET_PAGE);
    }
}



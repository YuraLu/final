package by.epam.trjava.tutorsystem.controller.command.impl;

import by.epam.trjava.tutorsystem.controller.command.Command;
import by.epam.trjava.tutorsystem.controller.command.util.CreatorFullURL;
import by.epam.trjava.tutorsystem.entity.User;
import by.epam.trjava.tutorsystem.service.ServiceProvider;
import by.epam.trjava.tutorsystem.service.UserService;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static by.epam.trjava.tutorsystem.controller.command.BeanFieldJsp.*;


public class CommandAuthorization implements Command {

    private static final String HOME_PAGE = "pages?command=showAllTests";
    private static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession oldSession;
        HttpSession newSession = null;

        String url = CreatorFullURL.create(request);

        String login = request.getParameter(USER_LOGIN);
        String password = request.getParameter(USER_PASSWORD);

        UserService userService = ServiceProvider.getInstance().getUserService();

        User user = null;
        String page = null;

        try {
            user = userService.authorization(login, password);
            if (user == null) {
                request.setAttribute(PARAMETER_WRONG_PARAMS, "true");
                page = LOGIN_PAGE;

                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Either username or password is wrong.</font>");
                dispatcher.include(request, response);

            } else {

                //get the old session and invalidate
                oldSession = request.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }
                //generate a new session
                newSession = request.getSession(true);

                //setting session to expiry in 5 mins
                newSession.setMaxInactiveInterval(5*60);

//                newSession.setAttribute(USER_OBJECT, user);
                newSession.setAttribute(USER_ID, user.getId());
                page = HOME_PAGE;
            }
        } catch (ServiceException e) {
            response.sendError(ERROR_NUMBER_500);
            throw new ServletException("Can't authorize in execute() CommandAuthorization", e);
        }

        newSession.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
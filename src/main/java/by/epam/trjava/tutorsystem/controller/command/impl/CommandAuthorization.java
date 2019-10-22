package by.epam.trjava.tutorsystem.controller.command.impl;

import by.epam.trjava.tutorsystem.controller.command.Command;
import by.epam.trjava.tutorsystem.controller.command.util.CreatorFullURL;
import by.epam.trjava.tutorsystem.entity.User;
import by.epam.trjava.tutorsystem.service.RoleService;
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

    private static final String HOME_PAGE = "/pages?command=showAllTests";
    private static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        final HttpSession session = request.getSession();

        String url = CreatorFullURL.create(request);

        final String login = request.getParameter(USER_LOGIN);
        final String password = request.getParameter(USER_PASSWORD);

        final UserService userService = ServiceProvider.getInstance().getUserService();

        String page = null;
        try {
            final User user = userService.authorization(login, password);
            if (user == null) {
                request.setAttribute(PARAMETER_WRONG_PARAMS, "true");
                page = LOGIN_PAGE;

            } else {

//                //setting session to expiry in 5 mins
//                session.setMaxInactiveInterval(5 * 60);

                session.setAttribute(USER_OBJECT, user);
                page = HOME_PAGE;
            }
        } catch (ServiceException e) {
            response.sendError(ERROR_NUMBER_500);
            throw new ServletException("Can't authorize in execute() CommandAuthorization", e);
        }

        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}

// fix registration and logIn
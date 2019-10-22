package by.epam.trjava.tutorsystem.controller.command.impl;

import by.epam.trjava.tutorsystem.controller.command.Command;
import by.epam.trjava.tutorsystem.entity.Test;
import by.epam.trjava.tutorsystem.entity.User;
import by.epam.trjava.tutorsystem.service.ServiceProvider;
import by.epam.trjava.tutorsystem.service.TestService;
import by.epam.trjava.tutorsystem.service.UserService;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.trjava.tutorsystem.controller.command.BeanFieldJsp.*;

public class CommandEditUser implements Command {
    private static final String TARGET_PAGE = "pages?command=viewPersonalCabinet";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final HttpSession session = request.getSession();

        int id = (int) session.getAttribute(USER_ID);

        String password = request.getParameter(USER_PASSWORD);
        String newPassword = request.getParameter(USER_NEW_PASSWORD);

        final UserService service = ServiceProvider.getInstance().getUserService();

        try {
            User user = service.findUser(id);
            service.updateUser(user, newPassword);
        } catch (ServiceException e) {
            throw new ServletException("Can't find and edit test in execute() CommandEditTest", e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(TARGET_PAGE);
        dispatcher.forward(request, response);
    }
}

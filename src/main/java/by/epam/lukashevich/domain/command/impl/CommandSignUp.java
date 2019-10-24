package by.epam.lukashevich.domain.command.impl;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.entity.user.Role;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.config.JSPPages.*;

public class CommandSignUp implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        final UserService userService = ServiceProvider.getInstance().getUserService();

        final String name = request.getParameter(USER_NAME);
        final String email = request.getParameter(USER_EMAIL);
        final String login = request.getParameter(USER_LOGIN);
        final String password = request.getParameter(USER_PASSWORD);
        final String roleName = request.getParameter(USER_ROLE);
        final Role role = Role.valueOf(roleName.toUpperCase());

        String redirectPage = INDEX;
        try {
            userService.registration(login, password, email, name, role);
            final User user = userService.authorization(login, password);
            redirectPage = USER_CABINET_PAGE;
            session.setAttribute(USER_ID, user.getId());
            session.setAttribute(USER_ROLE_ID, role.getId());
            response.sendRedirect(redirectPage);
        } catch (ServiceException e) {
            response.sendRedirect(redirectPage);
            throw new CommandException("Can't signUp in execute() CommandSignUp", e);
        }
    }
}

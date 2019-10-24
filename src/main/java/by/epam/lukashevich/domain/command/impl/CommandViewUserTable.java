package by.epam.lukashevich.domain.command.impl;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.config.JSPPages.USER_TABLE_PAGE;

public class CommandViewUserTable implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        final UserService userService = ServiceProvider.getInstance().getUserService();

        try {
            List<User> list = userService.findAll();
            request.setAttribute(USERS_LIST, list);
            request.getRequestDispatcher(USER_TABLE_PAGE).forward(request, response);
        } catch (ServiceException e) {
            throw new CommandException("Can't get list of users in execute() CommandViewUserTable", e);
        }
    }
}

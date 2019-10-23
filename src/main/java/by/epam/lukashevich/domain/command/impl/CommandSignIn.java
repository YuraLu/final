package by.epam.lukashevich.domain.command.impl;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.JSPPages.*;


public class CommandSignIn implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CommandSignIn.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();

        final String login = request.getParameter(USER_LOGIN);
        final String password = request.getParameter(USER_PASSWORD);

        final UserService userService = ServiceProvider.getInstance().getUserService();

        String page;
        try {
            final User user = userService.authorization(login, password);
            if (user == null) {
                request.setAttribute(PARAMETER_WRONG_PARAMS, "true");
                page = LOGIN_PAGE;
            } else {
                session.setAttribute(USER_ID, user.getId());
                session.setAttribute(USER_ROLE_ID, user.getRole().getRoleId());
                page = USER_CABINET_PAGE;
            }
        } catch (ServiceException e) {
            response.sendError(ERROR_NUMBER_500);
            throw new CommandException("Can't authorize in execute() CommandSignIn", e);
        }
        response.sendRedirect(page);
    }
}
package by.epam.lukashevich.domain.command.impl.user;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.service.provider.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.config.JSPActionCommand.VIEW_USER_CABINET_COMMAND;
import static by.epam.lukashevich.domain.config.JSPPage.USER_CABINET_PAGE;
import static by.epam.lukashevich.domain.config.Message.MESSAGE_DATA_CHANGED;
import static by.epam.lukashevich.domain.config.Message.MESSAGE_INVALID_INFO;

public class CommandUpdateUserPassword implements Command {

    private final UserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();

        final String currentPassword = request.getParameter(USER_PASSWORD);
        final String newPassword = request.getParameter(USER_NEW_PASSWORD);
        final String confirmedPassword = request.getParameter(USER_CONFIRMED_PASSWORD);
        final int userId = (int) session.getAttribute(USER_ID);

        try {
            userService.updatePassword(userId, currentPassword, newPassword, confirmedPassword);
            session.setAttribute(MESSAGE_TO_EDIT_USER, MESSAGE_DATA_CHANGED);
        } catch (ServiceException e) {
            session.setAttribute(MESSAGE_TO_EDIT_USER, MESSAGE_INVALID_INFO);
        }

        session.setAttribute(REDIRECT_COMMAND, VIEW_USER_CABINET_COMMAND);
        return USER_CABINET_PAGE;
    }
}
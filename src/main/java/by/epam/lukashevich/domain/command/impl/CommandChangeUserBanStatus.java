package by.epam.lukashevich.domain.command.impl;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.config.JSPActionCommand.VIEW_USER_CABINET;
import static by.epam.lukashevich.domain.util.config.JSPPages.USER_TABLE_PAGE;

public class CommandChangeUserBanStatus implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter(USER_FOR_ACTION));
        final UserService userService = ServiceProvider.getInstance().getUserService();

        try {
            userService.updateBanStatus(id);
            session.setAttribute(REDIRECT_COMMAND, VIEW_USER_CABINET);
            response.sendRedirect(USER_TABLE_PAGE);
        } catch (ServiceException e) {
            throw new CommandException("Can't update ban status in CommandChangeUserBanStatus", e);
        }
    }
}
package by.epam.lukashevich.domain.command.impl.user;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.service.provider.ServiceProvider;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.REDIRECT_COMMAND;
import static by.epam.lukashevich.domain.config.BeanFieldJsp.USER_FOR_ACTION;
import static by.epam.lukashevich.domain.config.JSPActionCommand.VIEW_USER_TABLE_COMMAND;
import static by.epam.lukashevich.domain.config.JSPPages.USER_TABLE_PAGE;

public class CommandChangeUserStatus implements Command {

    private final UserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();

        try {
            int id = Integer.parseInt(request.getParameter(USER_FOR_ACTION));
            userService.updateStatus(id);
        } catch (ServiceException e) {
            throw new CommandException("Can't update user status in CommandChangeUserStatus", e);
        }

        session.setAttribute(REDIRECT_COMMAND, VIEW_USER_TABLE_COMMAND);
        return USER_TABLE_PAGE;
    }
}
package by.epam.lukashevich.domain.command.impl;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.JSPPages.USER_TABLE_PAGE;

public class CommandChangeUserBanStatus implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CommandChangeUserBanStatus.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {

        int id = Integer.parseInt(request.getParameter(USER_ID));

        final UserService userService = ServiceProvider.getInstance().getUserService();

        try {
            userService.updateBanStatus(id);
        } catch (ServiceException e) {
            throw new CommandException("Can't update ban status in CommandChangeUserBanStatus", e);
        }
        request.getRequestDispatcher(USER_TABLE_PAGE).forward(request, response);
    }
}

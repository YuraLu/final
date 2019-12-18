package by.epam.lukashevich.domain.command.impl.user;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.command.impl.util.CheckMessage;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.service.provider.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.MESSAGE_TO_EDIT_USER;
import static by.epam.lukashevich.domain.config.BeanFieldJsp.USERS_LIST;
import static by.epam.lukashevich.domain.config.JSPPage.USER_TABLE_PAGE;

public class CommandViewUserTable implements Command {

    private UserService userService = ServiceProvider.getInstance().getUserService();

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();

        try {
            final List<User> list = userService.findAll();
            request.setAttribute(USERS_LIST, list);

            CheckMessage.checkMessageToJsp(session, request, MESSAGE_TO_EDIT_USER);

        } catch (ServiceException e) {
            throw new CommandException("Can't get list of users in execute() CommandViewUserTable", e);
        }
        return USER_TABLE_PAGE;
    }
}
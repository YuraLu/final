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

import static by.epam.lukashevich.domain.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.config.JSPPage.USER_CABINET_PAGE;

public class CommandViewUserCabinet implements Command {

    private UserService userService = ServiceProvider.getInstance().getUserService();

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);

        try {
            final User user = userService.findById(userId);

            request.setAttribute(USER_OBJECT, user);

            CheckMessage.checkMessageToJsp(session, request,
                    MESSAGE_TO_JSP_PASSWORD, MESSAGE_TO_EDIT_USER);

        } catch (ServiceException e) {
            throw new CommandException("Can't find userId in execute() CommandViewUserCabinet", e);
        }
        return USER_CABINET_PAGE;
    }
}
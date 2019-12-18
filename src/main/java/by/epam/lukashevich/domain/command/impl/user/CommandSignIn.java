package by.epam.lukashevich.domain.command.impl.user;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.service.exception.user.BannedUserServiceException;
import by.epam.lukashevich.domain.service.exception.user.InvalidUserInformationException;
import by.epam.lukashevich.domain.service.provider.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.config.JSPPage.SIGN_IN_PAGE;
import static by.epam.lukashevich.domain.config.Message.*;

public class CommandSignIn implements Command {

    private UserService userService = ServiceProvider.getInstance().getUserService();

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        final String login = request.getParameter(USER_LOGIN);
        final String password = request.getParameter(USER_PASSWORD);

        try {
            final User user = userService.signIn(login, password);

            session.setAttribute(USER_ID, user.getId());
            session.setAttribute(USER_ROLE_ID, user.getRole().getId());
            session.setAttribute(MESSAGE_TO_JSP, MESSAGE_SUCCESSFUL_LOGIN);

        } catch (BannedUserServiceException e) {
            session.setAttribute(MESSAGE_TO_JSP, MESSAGE_USER_IS_BANNED);
        } catch (InvalidUserInformationException e) {
            session.setAttribute(MESSAGE_TO_JSP, MESSAGE_INVALID_SIGN_PARAMETERS);
        } catch (ServiceException e) {
            throw new CommandException("Can't authorize in execute() CommandSignIn", e);
        }

        return SIGN_IN_PAGE;
    }
}
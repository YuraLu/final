package by.epam.lukashevich.domain.command.impl.user;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.user.Role;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.service.exception.user.InvalidEmailException;
import by.epam.lukashevich.domain.service.exception.user.InvalidLoginException;
import by.epam.lukashevich.domain.service.exception.user.InvalidUserInformationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.config.JSPActionCommand.VIEW_SIGN_UP_PAGE_COMMAND;
import static by.epam.lukashevich.domain.util.config.JSPPages.*;

public class CommandSignUp implements Command {

    private final UserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();

        final String name = request.getParameter(USER_NAME);
        final String email = request.getParameter(USER_EMAIL);
        final String login = request.getParameter(USER_LOGIN);
        final String password = request.getParameter(USER_PASSWORD);
        final String confirmedPassword = request.getParameter(USER_CONFIRMED_PASSWORD);
        final String roleName = request.getParameter(USER_ROLE);
        final Role role = Role.valueOf(roleName.toUpperCase());

        String redirectPage = SIGN_UP_PAGE;
        try {
            userService.registration(login, password, confirmedPassword, name, email, role);
            final User user = userService.signIn(login, password);

            session.setAttribute(MESSAGE_TO_SIGN_UP, "message.successful_registration");
            session.setAttribute(USER_ID, user.getId());
            session.setAttribute(USER_ROLE_ID, role.getId());

            redirectPage = USER_CABINET_PAGE;
        } catch (InvalidLoginException ex) {
            session.setAttribute(MESSAGE_TO_SIGN_UP, "message.invalid_username");
        } catch (InvalidEmailException ex) {
            session.setAttribute(MESSAGE_TO_SIGN_UP, "message.invalid_email");
        } catch (InvalidUserInformationException ex) {
            session.setAttribute(MESSAGE_TO_SIGN_UP, "message.invalid_info");
        } catch (ServiceException e) {
            throw new CommandException("Can't signUp in execute() CommandSignUp", e);
        }

        session.setAttribute(REDIRECT_COMMAND, VIEW_SIGN_UP_PAGE_COMMAND);
        return USER_CABINET_PAGE;
    }
}
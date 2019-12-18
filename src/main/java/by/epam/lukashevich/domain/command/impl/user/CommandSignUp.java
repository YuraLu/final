package by.epam.lukashevich.domain.command.impl.user;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.user.Role;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.service.exception.user.EmptyUserInformationException;
import by.epam.lukashevich.domain.service.exception.user.InvalidEmailException;
import by.epam.lukashevich.domain.service.exception.user.InvalidLoginException;
import by.epam.lukashevich.domain.service.exception.user.InvalidUserInformationException;
import by.epam.lukashevich.domain.service.provider.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.config.JSPActionCommand.VIEW_USER_CABINET_COMMAND;
import static by.epam.lukashevich.domain.config.JSPPage.SIGN_UP_PAGE_FORWARD;
import static by.epam.lukashevich.domain.config.JSPPage.USER_CABINET_PAGE;
import static by.epam.lukashevich.domain.config.Message.*;

public class CommandSignUp implements Command {

    private UserService userService = ServiceProvider.getInstance().getUserService();

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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

        String redirectPage = SIGN_UP_PAGE_FORWARD;

        try {
            userService.registration(login, password, confirmedPassword, name, email, role);
            final User user = userService.signIn(login, password);

            session.setAttribute(MESSAGE_TO_SIGN_UP, MESSAGE_SUCCESSFUL_REGISTRATION);
            session.setAttribute(USER_ID, user.getId());
            session.setAttribute(USER_ROLE_ID, role.getId());

            session.setAttribute(REDIRECT_COMMAND, VIEW_USER_CABINET_COMMAND);
            redirectPage = USER_CABINET_PAGE;

        } catch (EmptyUserInformationException e) {
            session.setAttribute(MESSAGE_TO_SIGN_UP, MESSAGE_DATA_INVALID_INFO);
        } catch (InvalidUserInformationException e) {
            session.setAttribute(MESSAGE_TO_SIGN_UP, MESSAGE_INVALID_INFO);
        } catch (InvalidLoginException e) {
            session.setAttribute(MESSAGE_TO_SIGN_UP, MESSAGE_INVALID_LOGIN);
        } catch (InvalidEmailException e) {
            session.setAttribute(MESSAGE_TO_SIGN_UP, MESSAGE_INVALID_EMAIL);
        } catch (ServiceException e) {
            throw new CommandException("Can't signUp in execute() CommandSignUp", e);
        }

        return redirectPage;
    }
}
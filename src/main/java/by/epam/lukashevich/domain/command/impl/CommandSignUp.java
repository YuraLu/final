package by.epam.lukashevich.domain.command.impl;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.entity.user.UserRole;
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

public class CommandSignUp implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CommandSignUp.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final HttpSession session = request.getSession();

        String redirectPage = LOGIN_PAGE;

        String name = request.getParameter(USER_NAME);
        String email = request.getParameter(USER_EMAIL);
        String login = request.getParameter(USER_LOGIN);
        String password = request.getParameter(USER_PASSWORD);
        String userRoleName = request.getParameter(USER_ROLE);

        final UserService userService = ServiceProvider.getInstance().getUserService();
        User user = null;
        UserRole role = UserRole.valueOf(userRoleName.toUpperCase());

        try {
            if (userService.registration(login, password, email, name, role)) {
                user = userService.authorization(login, password);
                redirectPage = USER_CABINET_PAGE;
            }
        } catch (ServiceException e) {
            response.sendRedirect(redirectPage);
        }

        if (user!=null){
            session.setAttribute(USER_ID, user.getId());
            session.setAttribute(USER_ROLE_ID, role.getRoleId());
        }
        response.sendRedirect(redirectPage);
    }
}

package by.epam.trjava.tutorsystem.controller.command.impl;

import by.epam.trjava.tutorsystem.controller.command.Command;
import by.epam.trjava.tutorsystem.controller.command.util.CreatorFullURL;
import by.epam.trjava.tutorsystem.entity.Role;
import by.epam.trjava.tutorsystem.entity.User;
import by.epam.trjava.tutorsystem.service.RoleService;
import by.epam.trjava.tutorsystem.service.ServiceProvider;
import by.epam.trjava.tutorsystem.service.UserService;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.trjava.tutorsystem.controller.command.BeanFieldJsp.*;

public class CommandRegistration implements Command {

    private static final String SUCCESSFUL_REDIRECT_PAGE_URL = "/pages?command=goToShowAllTestsPageCommand";
    private static final String UNSUCCESSFUL_REDIRECT_PAGE_URL = "/pages?command=goToRegistrationPage&wrong_params=true";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final HttpSession session = request.getSession();

        String redirectPage = UNSUCCESSFUL_REDIRECT_PAGE_URL;

        String userRoleName = request.getParameter(USER_ROLE);
        String name = request.getParameter(USER_NAME);
        String email = request.getParameter(USER_EMAIL);
        String login = request.getParameter(USER_LOGIN);
        String password = request.getParameter(USER_PASSWORD);

        String url = CreatorFullURL.create(request);

        final RoleService roleService = ServiceProvider.getInstance().getRoleService();
        Role role;

        try {
            role = roleService.findRoleIdByName(userRoleName);
        } catch (ServiceException e) {
            throw new ServletException("Can't find role by name in execute() CommandRegistration", e);
        }

        final UserService userService = ServiceProvider.getInstance().getUserService();
        User user = null;
        try {
            if (userService.registration(login, password, email, name, role.getId())) {
                user = userService.authorization(login, password);
                redirectPage = SUCCESSFUL_REDIRECT_PAGE_URL;
            }
        } catch (ServiceException e) {
            response.sendRedirect(redirectPage);
        }

        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
        if (user != null) {
            session.setAttribute(USER_OBJECT, user);
        }
        response.sendRedirect(redirectPage);
    }
}

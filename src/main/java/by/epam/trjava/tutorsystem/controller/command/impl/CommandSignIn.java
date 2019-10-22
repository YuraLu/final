package by.epam.trjava.tutorsystem.controller.command.impl;

import by.epam.trjava.tutorsystem.controller.command.Command;
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


public class CommandSignIn implements Command {

    private static final String TARGET_PAGE = "pages?command=showAllTests";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String login = request.getParameter(USER_LOGIN);
        final String password = request.getParameter(USER_PASSWORD);

        final UserService userService = ServiceProvider.getInstance().getUserService();
        final RoleService roleService = ServiceProvider.getInstance().getRoleService();
        final HttpSession session = request.getSession();

        try {
            final User user = userService.authorization(login, password);
            final Role role = roleService.findById(user.getRoleId());
            session.setAttribute(USER_ID, user.getId());
            session.setAttribute(USER_NAME, user.getName());
            session.setAttribute(USER_ROLE, role.getName());
            session.setAttribute("signInMessage", "message.successful_login");
        } catch (ServiceException e) {
            session.setAttribute("signInMessage", "message.invalid_sign_parameters");
            throw new ServletException("Problems with login in execute() CommandSignIn", e);
        }
        response.sendRedirect(TARGET_PAGE);
    }
}

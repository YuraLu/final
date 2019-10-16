package by.epam.trjava.tutorsystem.controller.command.impl;

import by.epam.trjava.tutorsystem.controller.command.Command;
import by.epam.trjava.tutorsystem.controller.command.util.CreatorFullURL;
import by.epam.trjava.tutorsystem.entity.User;
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

    private static final String SUCCESSFUL_REDIRECT_PAGE_URL = "pages?command=goToShowAllTestsPageCommand";
    private static final String UNSUCCESSFUL_REDIRECT_PAGE_URL = "pages?command=goToRegistrationPage&wrong_params=true";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirectPage = UNSUCCESSFUL_REDIRECT_PAGE_URL;
        String name = request.getParameter(USER_NAME);
        String email = request.getParameter(USER_EMAIL);
        String login = request.getParameter(USER_LOGIN);
        String password = request.getParameter(USER_PASSWORD);
        String url = CreatorFullURL.create(request);

        HttpSession session = request.getSession(true);

        UserService userService = ServiceProvider.getInstance().getUserService();
        User user = null;

        try {
            user = createUser(name, email, login, password);
            if (userService.registration(user)) {
                user = userService.authorization(login, password);
                redirectPage = SUCCESSFUL_REDIRECT_PAGE_URL;
            }
        } catch (ServiceException e) {
            response.sendError(ERROR_NUMBER_500);
            throw new ServletException("Problems with registration in execute() CommandRegistration", e);
        }

        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
        session.setAttribute(USER_ID, user.getId());

        response.sendRedirect(redirectPage);
    }

    private User createUser(String name, String email, String login, String password) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
        return user;
    }
}

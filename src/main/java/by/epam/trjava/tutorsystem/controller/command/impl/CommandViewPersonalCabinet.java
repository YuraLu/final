package by.epam.trjava.tutorsystem.controller.command.impl;

import by.epam.trjava.tutorsystem.controller.command.Command;
import by.epam.trjava.tutorsystem.controller.command.util.CreatorFullURL;
import by.epam.trjava.tutorsystem.entity.User;
import by.epam.trjava.tutorsystem.service.ServiceProvider;
import by.epam.trjava.tutorsystem.service.UserService;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.trjava.tutorsystem.controller.command.BeanFieldJsp.*;

public class CommandViewPersonalCabinet implements Command {

    private static final String REDIRECT_PAGE_URL = "pages?command=goToPersonalCabinetPageCommand";
    private static final String TARGET_PAGE = "/WEB-INF/jsp/personalCabinet.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final HttpSession session = request.getSession();
        String url = CreatorFullURL.create(request);

//        int id = (int) session.getAttribute(USER_ID);
//        UserService userService = ServiceProvider.getInstance().getUserService();
//        User user = null;
//
//        try {
//            user = userService.findUser(String.valueOf(id));
//        } catch (ServiceException e) {
//            response.sendError(ERROR_NUMBER_500);
//            throw new ServletException("Can't find User in execute() CommandPersonalCabinet", e);
//        }
////        session.setAttribute(USER_OBJECT, user);
//        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
//        response.sendRedirect(REDIRECT_PAGE_URL);



            session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
            RequestDispatcher dispatcher = request.getRequestDispatcher(TARGET_PAGE);
            dispatcher.forward(request, response);


    }
}

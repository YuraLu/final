package by.epam.lukashevich.controller;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.provider.CommandProvider;
import by.epam.lukashevich.domain.command.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.ALLOWED;
import static by.epam.lukashevich.domain.config.BeanFieldJsp.REDIRECT_COMMAND;
import static by.epam.lukashevich.domain.config.JSPActionCommand.PARAMETER_COMMAND;
import static by.epam.lukashevich.domain.config.JSPPages.ERROR_PAGE;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(Controller.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final boolean permitted = (boolean) request.getAttribute(ALLOWED);

        if (permitted) {
            final String commandName = request.getParameter(PARAMETER_COMMAND);
            logger.info("Do GET command name  " + commandName);

            try {
                final Command command = CommandProvider.getInstance().getCommand(commandName);
                final String path = command.execute(request, response);
                logger.info("Do GET forward to path - " + path);

                request.getRequestDispatcher(path).forward(request, response);
            } catch (CommandException e) {
                logger.error(e);
                response.sendRedirect(ERROR_PAGE);
            }
        } else {
            final String redirectTo = (String) request.getAttribute(REDIRECT_COMMAND);
            logger.info("in Do GET ELSE redirect to path - " + redirectTo);

            response.sendRedirect(redirectTo);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final boolean permitted = (boolean) request.getAttribute(ALLOWED);

        if (permitted) {
            final String commandName = request.getParameter(PARAMETER_COMMAND);
            logger.info("Do POST command name  " + commandName);

            try {
                final Command command = CommandProvider.getInstance().getCommand(commandName);
                final String path = command.execute(request, response);
                logger.info("in Do POST redirect to path - " + path);
                response.sendRedirect(path);
            } catch (CommandException e) {
                logger.error(e);
                response.sendRedirect(ERROR_PAGE);
            }
        } else {
            final String redirectTo = (String) request.getAttribute(REDIRECT_COMMAND);
            logger.info("in Do POST ELSE redirect to path - " + redirectTo);

            response.sendRedirect(redirectTo);
        }
    }
}
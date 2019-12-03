package by.epam.lukashevich.controller;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.CommandProvider;
import by.epam.lukashevich.domain.command.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.ALLOWED;
import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.REDIRECT_COMMAND;
import static by.epam.lukashevich.domain.util.config.JSPActionCommand.PARAMETER_COMMAND;
import static by.epam.lukashevich.domain.util.config.JSPPages.ERROR_PAGE;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(Controller.class);

    public Controller() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final boolean allowed = (boolean) request.getAttribute(ALLOWED);

        if (allowed) {
            final String commandName = request.getParameter(PARAMETER_COMMAND);
            logger.info("Do GET command name  " + commandName);

            System.out.println("in get command - " + commandName);

            try {
                final Command command = CommandProvider.getInstance().getCommand(commandName);
                final String path = command.execute(request, response);
                System.out.println("in get forward to path - " + path);

                request.getRequestDispatcher(path).forward(request, response);
            } catch (CommandException e) {
                logger.error(e);
                response.sendRedirect(ERROR_PAGE);
            }
        } else {
            final String redirectTo = (String) request.getAttribute(REDIRECT_COMMAND);
            System.out.println("in Get ELSE redirect to path - " + redirectTo);

            response.sendRedirect(redirectTo);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final boolean allowed = (boolean) request.getAttribute(ALLOWED);

        if (allowed) {
            final String commandName = request.getParameter(PARAMETER_COMMAND);
            logger.info("Do POST command name  " + commandName);

            System.out.println("in post command - " + commandName);
            try {
                final Command command = CommandProvider.getInstance().getCommand(commandName);
                final String path = command.execute(request, response);

                System.out.println("in post redirect to path - " + path);

                response.sendRedirect(path);
            } catch (CommandException e) {
                logger.error(e);
                response.sendRedirect(ERROR_PAGE);
            }
        } else {
            final String redirectTo = (String) request.getAttribute(REDIRECT_COMMAND);
            System.out.println("in post ELSE redirect to path - " + redirectTo);

            response.sendRedirect(redirectTo);
        }
    }
}
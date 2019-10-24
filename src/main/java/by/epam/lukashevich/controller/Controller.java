package by.epam.lukashevich.controller;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.CommandProvider;
import by.epam.lukashevich.domain.command.exception.CommandException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.*;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    public Controller() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter(PARAMETER_COMMAND);
        Command command = CommandProvider.getInstance().getCommand(commandName);

        System.out.println(commandName);
        try {
            command.execute(request, response);
        } catch (CommandException e) {
            LOGGER.error("CommandException in Controller", e);
            response.sendError(ERROR_NUMBER_500);
            e.printStackTrace();
        }
    }
}
package by.epam.trjava.tutorsystem.controller;

import by.epam.trjava.tutorsystem.controller.command.Command;
import by.epam.trjava.tutorsystem.controller.command.CommandProvider;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pages")
public class Controller extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Controller.class);
    private static final Integer ERROR_NUMBER_500 = 500;
    private static final long serialVersionUID = 1L;
    private static final String PARAMETER_COMMAND = "command";

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
        try {
            command.execute(request, response);
        } catch (Exception e) {
            LOGGER.error("Exception in Controller", e);
            response.sendError(ERROR_NUMBER_500);
        }
    }
}
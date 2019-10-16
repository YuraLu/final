package by.epam.trjava.tutorsystem.controller.command.impl;

import by.epam.trjava.tutorsystem.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.trjava.tutorsystem.controller.command.BeanFieldJsp.ERROR_NUMBER_500;

public class CommandMissing implements Command {

    private static final Logger LOGGER = Logger.getLogger(CommandMissing.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LOGGER.error("No such command error");
        response.sendError(ERROR_NUMBER_500);
        throw new ServletException("There is no such command CommandMissing");
    }
}

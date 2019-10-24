package by.epam.lukashevich.domain.command.impl;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.ERROR_NUMBER_500;

public class CommandMissing implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        response.sendError(ERROR_NUMBER_500);
        throw new CommandException("There is no such command");
    }
}

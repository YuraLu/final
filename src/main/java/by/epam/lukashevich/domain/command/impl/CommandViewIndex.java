package by.epam.lukashevich.domain.command.impl;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.JSPPages.INDEX;

public class CommandViewIndex implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {
        final HttpSession session = request.getSession();
        request.getRequestDispatcher(INDEX).forward(request,response);
    }
}

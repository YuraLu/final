package by.epam.lukashevich.domain.command.impl;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.REDIRECT_COMMAND;
import static by.epam.lukashevich.domain.util.config.JSPActionCommand.VIEW_USER_CABINET;
import static by.epam.lukashevich.domain.util.config.JSPPages.INDEX;

public class CommandSignOut implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        session.setAttribute(REDIRECT_COMMAND, VIEW_USER_CABINET);
        response.sendRedirect(INDEX);
    }
}



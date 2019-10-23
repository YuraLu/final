package by.epam.lukashevich.domain.command.impl;

import by.epam.lukashevich.domain.command.Command;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.JSPPages.LOGIN_PAGE;

public class CommandSignOut implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CommandSignOut.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(LOGIN_PAGE);
    }
}



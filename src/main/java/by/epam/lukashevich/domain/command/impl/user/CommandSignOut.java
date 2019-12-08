package by.epam.lukashevich.domain.command.impl.user;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.USER_ID;
import static by.epam.lukashevich.domain.config.BeanFieldJsp.USER_ROLE_ID;
import static by.epam.lukashevich.domain.config.JSPPages.SIGN_IN_PAGE;

public class CommandSignOut implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
//        if (session != null) {
//            session.invalidate();
//        }

        session.removeAttribute(USER_ID);
        session.removeAttribute(USER_ROLE_ID);

        return SIGN_IN_PAGE;
    }
}



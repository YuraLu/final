package by.epam.lukashevich.domain.command.impl.test;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.config.JSPActionCommand.VIEW_TEST_TABLE_COMMAND;
import static by.epam.lukashevich.domain.config.JSPPages.TEST_TABLE_PAGE;

/**
 * Aborts passing test by Student
 *
 * @author Lukashevich_Y_A
 */
public class CommandPassTestAbort implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        HttpSession session = request.getSession(false);

        session.removeAttribute(ASSIGNMENT_OBJECT);
        session.removeAttribute(NUMBER_OF_QUESTIONS);
        session.removeAttribute(CURRENT_QUESTION_NUMBER);

        session.setAttribute(REDIRECT_COMMAND, VIEW_TEST_TABLE_COMMAND);
        return TEST_TABLE_PAGE;
    }
}
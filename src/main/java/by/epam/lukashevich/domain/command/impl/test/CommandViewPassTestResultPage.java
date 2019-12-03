package by.epam.lukashevich.domain.command.impl.test;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.REDIRECT_COMMAND;
import static by.epam.lukashevich.domain.util.config.JSPActionCommand.VIEW_PASS_TEST_RESULT_PAGE_COMMAND;
import static by.epam.lukashevich.domain.util.config.JSPPages.PASS_TEST_QUESTION_PAGE;
import static by.epam.lukashevich.domain.util.config.JSPPages.PASS_TEST_RESULT_PAGE;

/**
 * Shows pass test result page
 *
 * @author Lukashevich_Y_A
 */
public class CommandViewPassTestResultPage implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        session.setAttribute(REDIRECT_COMMAND, VIEW_PASS_TEST_RESULT_PAGE_COMMAND);
        return PASS_TEST_RESULT_PAGE;
    }
}
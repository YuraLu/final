package by.epam.lukashevich.domain.command.impl.test;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.JSPPages.PASS_TEST_QUESTION_PAGE;

/**
 * Shows pass test question page
 *
 * @author Lukashevich_Y_A
 */
public class CommandViewPassTestQuestionPage implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        return PASS_TEST_QUESTION_PAGE;
    }
}
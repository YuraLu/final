package by.epam.lukashevich.domain.command.impl.test;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.entity.Assignment;
import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.service.AnswerService;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.config.JSPActionCommand.VIEW_PASS_TEST_QUESTION_PAGE_COMMAND;
import static by.epam.lukashevich.domain.util.config.JSPPages.INDEX_PAGE;
import static by.epam.lukashevich.domain.util.config.JSPPages.PASS_TEST_QUESTION_PAGE;

/**
 * Shows pass test page
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
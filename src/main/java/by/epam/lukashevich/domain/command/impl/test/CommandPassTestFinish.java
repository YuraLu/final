package by.epam.lukashevich.domain.command.impl.test;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.Assignment;
import by.epam.lukashevich.domain.service.AssignmentService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.service.provider.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.config.JSPActionCommand.VIEW_PASS_TEST_RESULT_PAGE_COMMAND;
import static by.epam.lukashevich.domain.config.JSPPage.PASS_TEST_RESULT_PAGE;

/**
 * Finishes passing test
 *
 * @author Lukashevich_Y_A
 */
public class CommandPassTestFinish implements Command {

    private final AssignmentService assignmentService = ServiceProvider.getInstance().getAssignmentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        final Assignment assignment = (Assignment) session.getAttribute(ASSIGNMENT_OBJECT);

        try {

            int assignmentId = assignmentService.addAndReturnId(assignment);
            assignment.setId(assignmentId);

            int score = assignmentService.getAssignmentScore(assignment);
            assignment.setScore(score);
            assignmentService.update(assignment);
            assignmentService.save(assignment);

            int replySize = assignment.getReplies().size();

            session.setAttribute(ASSIGNMENT_SCORE, score);
            session.setAttribute(PASS_TEST_FINISH_STATUS, true);
            session.setAttribute(REPLIES, replySize);

            session.removeAttribute(ASSIGNMENT_OBJECT);
            session.removeAttribute(NUMBER_OF_QUESTIONS);
            session.removeAttribute(CURRENT_QUESTION_NUMBER);

        } catch (ServiceException e) {
            throw new CommandException("Can't get test/user in execute() CommandPassTestFinish", e);
        }

        session.setAttribute(REDIRECT_COMMAND, VIEW_PASS_TEST_RESULT_PAGE_COMMAND);
        return PASS_TEST_RESULT_PAGE;
    }
}
package by.epam.lukashevich.domain.command.impl.test;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.Assignment;
import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.entity.Reply;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.*;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.util.builder.impl.AssignmentBuilderImpl;
import by.epam.lukashevich.domain.util.builder.impl.UserBuilderImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.config.JSPPages.PASS_TEST_PAGE;

/**
 * Shows pass test page
 *
 * @author Lukashevich_Y_A
 */
public class CommandViewPassTestPage implements Command {

    private final TestService testService = ServiceProvider.getInstance().getTestService();
    private final QuestionService questionService = ServiceProvider.getInstance().getQuestionService();
    private final AssignmentService assignmentService = ServiceProvider.getInstance().getAssignmentService();
    private final UserService userService =ServiceProvider.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        final int userId = (int) session.getAttribute(USER_ID);
        final int testId = Integer.parseInt(request.getParameter(TEST_ID));

        try {
            final Test test = testService.findById(testId);
            final List<Question> questionList = questionService.findAllQuestionsForTestId(testId);
            test.setQuestions(questionList);
            final int numberOfQuestions = questionList.size();

            final User user = userService.findById(userId);

            final Assignment assignment = new AssignmentBuilderImpl()
                    .withTest(test)
                    .withUser(user)
                    .build();

            int assignmentId = assignmentService.addAndReturnId(assignment);
            assignment.setId(assignmentId);

            int firstQuestion = 0;
            session.setAttribute(CURRENT_QUESTION_NUMBER, firstQuestion);
            session.setAttribute(NUMBER_OF_QUESTIONS, numberOfQuestions);
            session.setAttribute(ASSIGNMENT_OBJECT, assignment);

        } catch (ServiceException e) {
            throw new CommandException("Can't get test/user in execute() CommandViewPassTestPage", e);
        }

        return PASS_TEST_PAGE;
    }
}
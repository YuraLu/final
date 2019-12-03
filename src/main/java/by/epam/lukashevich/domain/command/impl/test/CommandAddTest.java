package by.epam.lukashevich.domain.command.impl.test;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.entity.Subject;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.*;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.util.builder.impl.TestBuilderImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.config.JSPActionCommand.VIEW_TEST_TABLE_COMMAND;
import static by.epam.lukashevich.domain.util.config.JSPPages.INDEX_PAGE;
import static by.epam.lukashevich.domain.util.config.JSPPages.TEST_TABLE_PAGE;

public class CommandAddTest implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final SubjectService subjectService = ServiceProvider.getInstance().getSubjectService();
        final UserService userService = ServiceProvider.getInstance().getUserService();
//        final QuestionService questionService = ServiceProvider.getInstance().getQuestionService();

        final HttpSession session = request.getSession();
        final TestService service = ServiceProvider.getInstance().getTestService();

        final String title = request.getParameter(TEST_TITLE);
        final String description = request.getParameter(TEST_DESCRIPTION);
        final int subjectId = Integer.parseInt(request.getParameter(TEST_SUBJECT_ID));
        final int authorId = Integer.parseInt(request.getParameter(TEST_AUTHOR_ID));
//        final String[] testQuestions = request.getParameterValues(TEST_QUESTIONS);

        if (title.isEmpty()) {
            throw new CommandException("No data in title field during Test add in execute() CommandAddQuestion");
        }

//        List<Question> questionList = new ArrayList<>();
//        try {
//            for (String testQuestion : testQuestions) {
//                Question question = questionService.findById(Integer.parseInt(testQuestion));
//                questionList.add(question);
//            }
//        } catch (ServiceException e) {
//            throw new CommandException("Can't add question to list in execute() CommandAddTest", e);
//        }

        try {
            Subject subject = subjectService.findById(subjectId);
            User author = userService.findById(authorId);

            Test test = new TestBuilderImpl().withTitle(title)
                    .withDescription(description)
                    .withSubject(subject)
//                    .withQuestions(questionList)
                    .withAuthor(author)
                    .build();
            service.add(test);

        } catch (ServiceException e) {
            response.sendRedirect(INDEX_PAGE);
            throw new CommandException("Can't add test in execute() CommandAddTest", e);
        }
        session.setAttribute(REDIRECT_COMMAND, VIEW_TEST_TABLE_COMMAND);
        return TEST_TABLE_PAGE;
    }
}
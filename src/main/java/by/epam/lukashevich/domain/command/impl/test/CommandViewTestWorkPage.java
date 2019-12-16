package by.epam.lukashevich.domain.command.impl.test;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.entity.Subject;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.service.AnswerService;
import by.epam.lukashevich.domain.service.QuestionService;
import by.epam.lukashevich.domain.service.SubjectService;
import by.epam.lukashevich.domain.service.TestService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.service.provider.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.config.JSPPage.TEST_WORK_PAGE;

public class CommandViewTestWorkPage implements Command {

    private final TestService service = ServiceProvider.getInstance().getTestService();
    private final SubjectService subjectService = ServiceProvider.getInstance().getSubjectService();
    private final QuestionService questionService = ServiceProvider.getInstance().getQuestionService();
    private final AnswerService answerService = ServiceProvider.getInstance().getAnswerService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final int testId = Integer.parseInt(request.getParameter(TEST_ID));

        try {
            Test test = service.findById(testId);
            List<Subject> subjectList = subjectService.findAll();
            List<Question> questionList = questionService.findAllQuestionsForTestId(testId);

            for (Question question : questionList) {
                List<Answer> answerList = answerService.findAllAnswersForQuestionId(question.getId());
                question.setAnswers(answerList);
            }

            test.setQuestions(questionList);

            request.setAttribute(TEST_OBJECT, test);
            request.setAttribute(SUBJECT_LIST, subjectList);
        } catch (ServiceException e) {
            throw new CommandException("Can't get list of tests in execute() CommandViewTestTable", e);
        }

        return TEST_WORK_PAGE;
    }
}
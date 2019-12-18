package by.epam.lukashevich.domain.command.impl.test;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.entity.Assignment;
import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.service.AnswerService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.service.provider.ServiceProvider;
import by.epam.lukashevich.domain.util.builder.impl.AnswerBuilderImpl;
import by.epam.lukashevich.domain.util.builder.impl.QuestionBuilderImpl;
import by.epam.lukashevich.domain.util.builder.impl.ReplyBuilderImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.config.JSPActionCommand.FINISH_TEST_COMMAND;
import static by.epam.lukashevich.domain.config.JSPActionCommand.VIEW_PASS_TEST_QUESTION_PAGE_COMMAND;
import static by.epam.lukashevich.domain.config.JSPPage.PASS_TEST_QUESTION_PAGE;
import static by.epam.lukashevich.domain.config.JSPPage.PASS_TEST_RESULT_PAGE;

/**
 * Prepares next and saves current page of passing test
 *
 * @author Lukashevich_Y_A
 */
public class CommandGetNextTestQuestion implements Command {

    private static final Logger logger = LogManager.getLogger(CommandPassTestAbort.class);
    private AnswerService answerService = ServiceProvider.getInstance().getAnswerService();

    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        final int numberOfQuestions = (int) session.getAttribute(NUMBER_OF_QUESTIONS);
        int currentQuestionNumber = (int) session.getAttribute(CURRENT_QUESTION_NUMBER);
        final Assignment assignment = (Assignment) session.getAttribute(ASSIGNMENT_OBJECT);
        final List<Question> questionList = assignment.getTest().getQuestions();

        int firstQuestion = 0;

        if (firstQuestion == 0) {
            assignment.setDate(new Date());
        }

        if (currentQuestionNumber != firstQuestion) {
            saveUserAnswer(request, assignment, currentQuestionNumber - 1);
        }

        if (currentQuestionNumber < numberOfQuestions) {
            prepareAndShowNextQuestion(session, questionList, currentQuestionNumber);
        }

        if (currentQuestionNumber == numberOfQuestions) {
            session.setAttribute(REDIRECT_COMMAND, FINISH_TEST_COMMAND);
            request.getRequestDispatcher(PASS_TEST_RESULT_PAGE).include(request, response);
        } else {
            session.setAttribute(REDIRECT_COMMAND, VIEW_PASS_TEST_QUESTION_PAGE_COMMAND);
        }
        return PASS_TEST_QUESTION_PAGE;
    }

    private void prepareAndShowNextQuestion(HttpSession session,
                                            List<Question> questionList,
                                            int currentQuestionNumber) throws CommandException {
        try {
            Question question = questionList.get(currentQuestionNumber++);
            List<Answer> answerList = answerService.findAllAnswersForQuestionId(question.getId());
            question.setAnswers(answerList);

            session.setAttribute(QUESTION_OBJECT, question);
            session.setAttribute(CURRENT_QUESTION_NUMBER, currentQuestionNumber);

        } catch (ServiceException e) {
            logger.info(e);
            throw new CommandException("Can't get question/answer in CommandGetNextTestQuestion()");
        }
    }

    private void saveUserAnswer(final HttpServletRequest request, Assignment assignment, int currentQuestionNumber) {
        final List<Answer> checkedAnswerList = new ArrayList<>();
        final String[] questionAnswerChecked = request.getParameterValues(ANSWER_CORRECT);

        for (String answerId : questionAnswerChecked) {
            Answer answer = new AnswerBuilderImpl(Integer.parseInt(answerId))
                    .isCorrect(true)
                    .build();
            checkedAnswerList.add(answer);
        }

        Question question = new QuestionBuilderImpl(
                assignment
                        .getTest()
                        .getQuestions()
                        .get(currentQuestionNumber)
                        .getId()).build();

        assignment.getReplies().add(new ReplyBuilderImpl()
                .withAnswers(checkedAnswerList)
                .withQuestion(question)
                .build());
    }
}
package by.epam.lukashevich.domain.command.impl.question;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.service.QuestionService;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.util.builder.impl.AnswerBuilderImpl;
import by.epam.lukashevich.domain.util.builder.impl.QuestionBuilderImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.config.JSPActionCommand.VIEW_TEST_WORK_PAGE_COMMAND;
import static by.epam.lukashevich.domain.util.config.JSPPages.TEST_WORK_PAGE;

public class CommandAddQuestion implements Command {

    private final QuestionService questionService = ServiceProvider.getInstance().getQuestionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        final String text = request.getParameter(QUESTION_TEXT);
        final int testId = Integer.parseInt(request.getParameter(TEST_ID));
        final String[] questionAnswers = request.getParameterValues(QUESTION_ANSWERS);
        final String[] questionAnswerCorrect = request.getParameterValues(ANSWER_CORRECT);


        if (text.isEmpty()) {
            throw new CommandException("No data in text field during Question add in execute() CommandAddQuestion");
        }

        List<Answer> answerList = getAnswerOptionList(questionAnswers, questionAnswerCorrect);

        Question question = new QuestionBuilderImpl()
                .withText(text)
                .withAnswerList(answerList)
                .build();

        try {
            questionService.addQuestionToTest(question, testId);
            request.setAttribute(TEST_ID, testId);

        } catch (ServiceException e) {
            throw new CommandException("Can't add question in execute() CommandAddQuestion", e);
        }

        session.setAttribute(REDIRECT_COMMAND, VIEW_TEST_WORK_PAGE_COMMAND + "&" + TEST_ID + "=" + testId);
        return TEST_WORK_PAGE;
    }

    private List<Answer> getAnswerOptionList(String[] questionAnswers, String[] questionAnswerCorrect) {
        List<Answer> answerList = new ArrayList<>();

        for (int i = 0; i < questionAnswers.length; i++) {
            if (!questionAnswers[i].isEmpty()) {
                Answer answer = new AnswerBuilderImpl()
                        .withText(questionAnswers[i])
                        .isCorrect(isAnswerCorrect(questionAnswerCorrect, i))
                        .build();
                answerList.add(answer);
            }
        }
        return answerList;
    }

    private boolean isAnswerCorrect(String[] questionAnswerCorrect, int answerNumber) {
        for (String s : questionAnswerCorrect) {
            if (s.equals(String.valueOf(answerNumber))) {
                return true;
            }
        }
        return false;
    }
}
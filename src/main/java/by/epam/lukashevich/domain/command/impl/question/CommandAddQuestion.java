package by.epam.lukashevich.domain.command.impl.question;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.service.AnswerService;
import by.epam.lukashevich.domain.service.QuestionService;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.util.builder.AnswerBuilder;
import by.epam.lukashevich.domain.util.builder.QuestionBuilder;
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
import static by.epam.lukashevich.domain.util.config.JSPActionCommand.VIEW_QUESTION_TABLE;
import static by.epam.lukashevich.domain.util.config.JSPPages.*;

public class CommandAddQuestion implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        final QuestionService questionService = ServiceProvider.getInstance().getQuestionService();

        final String text = request.getParameter(QUESTION_TEXT);
        final String[] questionAnswers = request.getParameterValues("questionAnswer[]");
        final String[] questionAnswerCorrect = request.getParameterValues("answerCorrect[]");

        if (text.isEmpty()) {
            throw new CommandException("No data in text field during Question add in execute() CommandAddQuestion");
        }

        List<Answer> answerList = new ArrayList<>();

        for (int i = 0; i < questionAnswers.length; i++) {
            if (!questionAnswers[i].isEmpty()) {
                Answer answer = new AnswerBuilderImpl()
                        .withText(questionAnswers[i])
                        .isCorrect(isAnswerCorrect(questionAnswerCorrect, i + 1))
                        .build();
                answerList.add(answer);
            }
        }

        Question question = new QuestionBuilderImpl()
                .withText(text)
                .withAnswerList(answerList)
                .build();

        try {
            questionService.add(question);
            session.setAttribute(REDIRECT_COMMAND, VIEW_QUESTION_TABLE);
            response.sendRedirect(QUESTION_TABLE_PAGE);
        } catch (ServiceException e) {
            response.sendRedirect(INDEX);
            throw new CommandException("Can't add question in execute() CommandAddQuestion", e);
        }
    }

    private boolean isAnswerCorrect(String[] questionAnswerCorrect, int answerNumber) {
        for (int i = 0; i < questionAnswerCorrect.length; i++) {
            if (questionAnswerCorrect[i].equals(String.valueOf(answerNumber))) {
                return true;
            }
        }
        return false;
    }
}
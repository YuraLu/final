package by.epam.lukashevich.domain.command.impl.question;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.service.QuestionService;
import by.epam.lukashevich.domain.service.ReplyService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.service.provider.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.config.JSPActionCommand.VIEW_TEST_WORK_PAGE_COMMAND;
import static by.epam.lukashevich.domain.config.JSPPage.TEST_WORK_PAGE;
import static by.epam.lukashevich.domain.config.Message.MESSAGE_DELETE_QUESTION_ERROR;
import static by.epam.lukashevich.domain.config.Message.MESSAGE_DELETE_SUBJECT_ERROR;

public class CommandDeleteQuestion implements Command {

    private QuestionService questionService = ServiceProvider.getInstance().getQuestionService();
    private ReplyService replyService = ServiceProvider.getInstance().getReplyService();

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void setReplyService(ReplyService replyService) {
        this.replyService = replyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();

        final int questionId = Integer.parseInt(request.getParameter(QUESTION_ID));
        final int testId = Integer.parseInt(request.getParameter(TEST_ID));

        try {

            if (!replyService.isQuestionReplied(questionId)){
                questionService.delete(questionId);
            }else {
                session.setAttribute(MESSAGE_ERRORS_TO_JSP, MESSAGE_DELETE_QUESTION_ERROR);
            }
        } catch (ServiceException e) {
            throw new CommandException("Can't delete question in execute() CommandDeleteQuestion", e);
        }

        session.setAttribute(REDIRECT_COMMAND, VIEW_TEST_WORK_PAGE_COMMAND + "&" + TEST_ID + "=" + testId);
        return TEST_WORK_PAGE;
    }
}
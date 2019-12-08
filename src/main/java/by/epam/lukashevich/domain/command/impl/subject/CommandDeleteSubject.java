package by.epam.lukashevich.domain.command.impl.subject;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.service.provider.ServiceProvider;
import by.epam.lukashevich.domain.service.SubjectService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.config.JSPActionCommand.VIEW_SUBJECT_TABLE_COMMAND;
import static by.epam.lukashevich.domain.config.JSPPages.SUBJECT_TABLE_PAGE;

public class CommandDeleteSubject implements Command {

    private final SubjectService subjectService = ServiceProvider.getInstance().getSubjectService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        final int id = Integer.parseInt(request.getParameter(SUBJECT_FOR_ACTION));

        try {
            subjectService.delete(id);
        } catch (ServiceException e) {
            session.setAttribute(MESSAGE_TO_JSP, "message.delete_subject_error");
        }
        session.setAttribute(REDIRECT_COMMAND, VIEW_SUBJECT_TABLE_COMMAND);
        return SUBJECT_TABLE_PAGE;
    }
}
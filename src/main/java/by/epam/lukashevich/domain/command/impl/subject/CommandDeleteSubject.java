package by.epam.lukashevich.domain.command.impl.subject;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.service.SubjectService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.service.provider.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.config.JSPActionCommand.VIEW_SUBJECT_TABLE_COMMAND;
import static by.epam.lukashevich.domain.config.JSPPage.SUBJECT_TABLE_PAGE;
import static by.epam.lukashevich.domain.config.Message.MESSAGE_DELETE_SUBJECT_ERROR;

public class CommandDeleteSubject implements Command {

    private SubjectService subjectService = ServiceProvider.getInstance().getSubjectService();

    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        final int id = Integer.parseInt(request.getParameter(SUBJECT_FOR_ACTION));

        try {
            subjectService.delete(id);
        } catch (ServiceException e) {
            session.setAttribute(MESSAGE_ERRORS_TO_JSP, MESSAGE_DELETE_SUBJECT_ERROR);
        }
        session.setAttribute(REDIRECT_COMMAND, VIEW_SUBJECT_TABLE_COMMAND);
        return SUBJECT_TABLE_PAGE;
    }
}
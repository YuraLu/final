package by.epam.lukashevich.domain.command.impl.subject;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.command.impl.util.CheckMessage;
import by.epam.lukashevich.domain.entity.Subject;
import by.epam.lukashevich.domain.service.SubjectService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.service.provider.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.MESSAGE_ERRORS_TO_JSP;
import static by.epam.lukashevich.domain.config.BeanFieldJsp.SUBJECT_LIST;
import static by.epam.lukashevich.domain.config.JSPPage.SUBJECT_TABLE_PAGE;

public class CommandViewSubjectTable implements Command {


    private SubjectService subjectService = ServiceProvider.getInstance().getSubjectService();

    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();

        try {

            List<Subject> list = subjectService.findAll();
            request.setAttribute(SUBJECT_LIST, list);

            CheckMessage.checkMessageToJsp(session, request, MESSAGE_ERRORS_TO_JSP);

        } catch (ServiceException e) {
            throw new CommandException("Can't get list of subjects in execute() CommandViewSubjectTable", e);
        }
        return SUBJECT_TABLE_PAGE;
    }
}
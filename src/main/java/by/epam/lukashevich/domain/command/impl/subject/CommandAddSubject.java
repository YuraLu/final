package by.epam.lukashevich.domain.command.impl.subject;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.Subject;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.SubjectService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.util.builder.SubjectBuilder;
import by.epam.lukashevich.domain.util.builder.impl.SubjectBuilderImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.*;
import static by.epam.lukashevich.domain.util.config.JSPActionCommand.VIEW_SUBJECT_TABLE;
import static by.epam.lukashevich.domain.util.config.JSPPages.*;

public class CommandAddSubject implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final HttpSession session = request.getSession();
        final SubjectService service = ServiceProvider.getInstance().getSubjectService();

        final String name = request.getParameter(SUBJECT_NAME);

        try {
            SubjectBuilder builder = new SubjectBuilderImpl();
            Subject subject = builder.withName(name)
                        .build();
            service.add(subject);
            session.setAttribute(REDIRECT_COMMAND, VIEW_SUBJECT_TABLE);
            response.sendRedirect(SUBJECT_TABLE_PAGE);
        } catch (ServiceException e) {
            response.sendRedirect(INDEX);
            throw new CommandException("Can't add subject in execute() CommandAddSubject", e);
        }
    }
}

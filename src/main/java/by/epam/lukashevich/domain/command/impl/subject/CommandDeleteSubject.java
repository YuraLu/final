package by.epam.lukashevich.domain.command.impl.subject;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.SubjectService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.REDIRECT_COMMAND;
import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.SUBJECT_FOR_ACTION;
import static by.epam.lukashevich.domain.util.config.JSPActionCommand.VIEW_SUBJECT_TABLE;
import static by.epam.lukashevich.domain.util.config.JSPPages.SUBJECT_TABLE_PAGE;

public class CommandDeleteSubject implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {
        final HttpSession session = request.getSession();
        final SubjectService service = ServiceProvider.getInstance().getSubjectService();
        final int id = Integer.parseInt(request.getParameter(SUBJECT_FOR_ACTION));

        try {
            service.delete(id);
            session.setAttribute(REDIRECT_COMMAND, VIEW_SUBJECT_TABLE);
            response.sendRedirect(SUBJECT_TABLE_PAGE);
        } catch (ServiceException e) {
            throw new CommandException("Can't delete test in execute() CommandDeleteTest", e);
        }
    }
}

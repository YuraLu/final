package by.epam.lukashevich.domain.command.impl.test;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.Subject;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.service.ServiceProvider;
import by.epam.lukashevich.domain.service.SubjectService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.lukashevich.domain.util.config.BeanFieldJsp.SUBJECT_LIST;
import static by.epam.lukashevich.domain.util.config.JSPPages.TEST_WORK_PAGE;

public class CommandViewTestAdd implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {

        final SubjectService subjectService = ServiceProvider.getInstance().getSubjectService();

        try {
            List<Subject> subjectList = subjectService.findAll();
            request.setAttribute(SUBJECT_LIST, subjectList);
            request.getRequestDispatcher(TEST_WORK_PAGE).forward(request, response);
        } catch (ServiceException e) {
            throw new CommandException("Can't get list of tests in execute() CommandViewTestTable", e);
        }
    }
}



package by.epam.lukashevich.domain.command.impl.subject;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.service.SubjectService;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.JSPPage.INDEX_PAGE;
import static by.epam.lukashevich.domain.config.JSPPage.SUBJECT_TABLE_PAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

public class CommandViewSubjectTableTest {

    @Test
    public void executeValid() throws CommandException, ServletException, IOException {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        SubjectService subjectService = mock(SubjectService.class);
        Command command = new CommandViewSubjectTable(subjectService);

        String result = command.execute(mockRequest, mockResponse);
        verify(mockRequest, never()).getSession();
        assertEquals(result, SUBJECT_TABLE_PAGE);
    }

    @Test
    public void executeNotValid() throws CommandException, ServletException, IOException {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        SubjectService subjectService = mock(SubjectService.class);
        Command command = new CommandViewSubjectTable(subjectService);

        String result = command.execute(mockRequest, mockResponse);
        verify(mockRequest, never()).getSession();
        assertNotEquals(result, INDEX_PAGE);
    }
}
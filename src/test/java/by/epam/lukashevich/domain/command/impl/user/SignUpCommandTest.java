package by.epam.lukashevich.domain.command.impl.user;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.provider.CommandProvider;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.user.InvalidLoginException;
import by.epam.lukashevich.domain.service.exception.user.UserServiceException;
import org.testng.annotations.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.JSPActionCommand.SIGN_UP_COMMAND;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class SignUpCommandTest {

    private final CommandProvider provider = CommandProvider.getInstance();

    @Test
    public void testExecute_signUp_correct()
            throws UserServiceException, CommandException, ServletException, IOException {
        //given
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        UserService userService = mock(UserService.class);
        HttpSession session = mock(HttpSession.class);
        //
        Command command = provider.getCommand(SIGN_UP_COMMAND);

        //when
        when(mockRequest.getParameter("login")).thenReturn("login");
        when(mockRequest.getParameter("password")).thenReturn("password");
        when(mockRequest.getParameter("confirmedPassword")).thenReturn("confirmedPassword");
        when(mockRequest.getParameter("name")).thenReturn("name");
        when(mockRequest.getParameter("email")).thenReturn("email");
        when(mockRequest.getParameter("role")).thenReturn("ADMIN");
        when(mockRequest.getSession()).thenReturn(session);
        doThrow(InvalidLoginException.class).when(userService).registration(
                anyString(), anyString(), anyString(),
                anyString(), anyString(), anyObject());
        String result = command.execute(mockRequest, mockResponse);
        //then
        assertEquals(result, "userCabinet");
    }


    @Test(expectedExceptions = CommandException.class)
    public void testExecute_signUp_badLogin()
            throws UserServiceException, CommandException, ServletException, IOException {
        //given
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        UserService userService = mock(UserService.class);
        HttpSession session = mock(HttpSession.class);
        //
        Command command = provider.getCommand(SIGN_UP_COMMAND);
        //when
        when(mockRequest.getParameter("login")).thenReturn("1");
        when(mockRequest.getParameter("password")).thenReturn("password");
        when(mockRequest.getParameter("confirmedPassword")).thenReturn("confirmedPassword");
        when(mockRequest.getParameter("name")).thenReturn("name");
        when(mockRequest.getParameter("email")).thenReturn("email");
        when(mockRequest.getParameter("role")).thenReturn("ADMIN");
        when(mockRequest.getSession()).thenReturn(session);
        //
        doThrow(CommandException.class).when(userService).registration(
                anyString(), anyString(), anyString(),
                anyString(), anyString(), anyObject());
        String result = command.execute(mockRequest, mockResponse);
        //then

        //exception
    }
}
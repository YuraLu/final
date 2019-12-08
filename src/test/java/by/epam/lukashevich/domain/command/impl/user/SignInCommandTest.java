package by.epam.lukashevich.domain.command.impl.user;

import by.epam.lukashevich.domain.command.Command;
import by.epam.lukashevich.domain.command.provider.CommandProvider;
import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.user.Role;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.service.exception.user.BannedUserServiceException;
import by.epam.lukashevich.domain.service.exception.user.InvalidUserInformationException;
import org.testng.annotations.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static by.epam.lukashevich.domain.config.JSPActionCommand.SIGN_IN_COMMAND;
import static by.epam.lukashevich.domain.config.JSPPages.INDEX_PAGE;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class SignInCommandTest {

    private final CommandProvider commandProvider = CommandProvider.getInstance();

    @Test(expectedExceptions = CommandException.class)
    public void testExecute_exceptionFromService_CommandException()
            throws CommandException, ServiceException, IOException, ServletException {

        //given
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        UserService userService = mock(UserService.class);
        Command command = commandProvider.getCommand(SIGN_IN_COMMAND);
        //when
        when(mockRequest.getParameter("login")).thenReturn("login");
        when(mockRequest.getParameter("password")).thenReturn("password");
        doThrow(ServiceException.class).when(userService).signIn(anyString(), anyString());
        command.execute(mockRequest,mockResponse);
        //then
        //assert exception
    }


    @Test
    public void testExecute_bannedUserExceptionFromSignIn()
            throws ServiceException, CommandException, ServletException, IOException {
        //given
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        UserService service = mock(UserService.class);
        User user = mock(User.class);
        Role role = Role.ADMIN;
        HttpSession session = mock(HttpSession.class);
        Command command = commandProvider.getCommand(SIGN_IN_COMMAND);
        //when
        when(mockRequest.getParameter("login")).thenReturn("Username");
        when(mockRequest.getParameter("password")).thenReturn("Password");
        when(mockRequest.getSession()).thenReturn(session);
        doReturn(user).when(service).signIn(anyString(), anyString());
        when(user.getRole()).thenReturn(role);
        doThrow(BannedUserServiceException.class).when(service).signIn(anyString(), anyString());
        String result = command.execute(mockRequest,mockResponse);
        //then
        assertEquals(result, INDEX_PAGE);
    }

    @Test
    public void execute_invalidUserInformationExceptionFromSignIn()
            throws ServiceException, CommandException, ServletException, IOException {
        //given
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        UserService service = mock(UserService.class);
        User user = mock(User.class);
        Role role = Role.ADMIN;
        HttpSession session = mock(HttpSession.class);
        Command command = commandProvider.getCommand(SIGN_IN_COMMAND);
        //when
        when(mockRequest.getParameter("login")).thenReturn("Username");
        when(mockRequest.getParameter("password")).thenReturn("Password");
        when(mockRequest.getSession()).thenReturn(session);
        doReturn(user).when(service).signIn(anyString(), anyString());
        when(user.getRole()).thenReturn(role);
        doThrow(InvalidUserInformationException.class).when(service).signIn(anyString(), anyString());
        String result = command.execute(mockRequest,mockResponse);
        //then
        assertEquals(result, INDEX_PAGE);
    }


    @Test
    public void execute_validParameters()
            throws ServiceException, CommandException, ServletException, IOException {
        //given
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        UserService service = mock(UserService.class);
        User user = mock(User.class);
        Role role = Role.ADMIN;
        HttpSession session = mock(HttpSession.class);
        Command command = commandProvider.getCommand(SIGN_IN_COMMAND);
        //when
        when(mockRequest.getParameter("login")).thenReturn("admin");
        when(mockRequest.getParameter("password")).thenReturn("qwerty789");
        when(mockRequest.getSession()).thenReturn(session);
        when(service.signIn(anyString(), anyString())).thenReturn(user);
        when(user.getRole()).thenReturn(role);

        String result = command.execute(mockRequest,mockResponse);
        //then
        assertEquals(result, INDEX_PAGE);
    }
}
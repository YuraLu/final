package by.epam.lukashevich.domain.command.impl.user;

import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.user.Role;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.ServiceException;
import by.epam.lukashevich.domain.service.exception.user.BannedUserServiceException;
import by.epam.lukashevich.domain.service.exception.user.InvalidUserInformationException;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.JSPPage.SIGN_IN_PAGE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SignInCommandTest {

    @Test(expected = CommandException.class)
    public void testExecute_exceptionFromService_CommandException()
            throws CommandException, ServiceException, IOException, ServletException {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        UserService userService = mock(UserService.class);
        CommandSignIn command = new CommandSignIn();
        command.setUserService(userService);

        when(mockRequest.getParameter("login")).thenReturn("login");
        when(mockRequest.getParameter("password")).thenReturn("password");
        doThrow(ServiceException.class).when(userService).signIn(anyString(), anyString());
        command.execute(mockRequest, mockResponse);
    }

    @Test
    public void testExecute_bannedUserExceptionFromSignIn()
            throws ServiceException, CommandException, ServletException, IOException {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpSession mockSession = mock(HttpSession.class);
        UserService userService = mock(UserService.class);
        CommandSignIn command = new CommandSignIn();
        command.setUserService(userService);

        User user = mock(User.class);
        Role role = Role.ADMIN;

        when(mockRequest.getParameter("login")).thenReturn("Username");
        when(mockRequest.getParameter("password")).thenReturn("Password");
        when(mockRequest.getSession()).thenReturn(mockSession);
        doReturn(user).when(userService).signIn(anyString(), anyString());
        when(user.getRole()).thenReturn(role);
        doThrow(BannedUserServiceException.class).when(userService).signIn(anyString(), anyString());
        String result = command.execute(mockRequest, mockResponse);

        assertEquals(result, SIGN_IN_PAGE);
    }

    @Test
    public void execute_invalidUserInformationExceptionFromSignIn()
            throws ServiceException, CommandException, ServletException, IOException {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpSession mockSession = mock(HttpSession.class);
        UserService userService = mock(UserService.class);
        CommandSignIn command = new CommandSignIn();
        command.setUserService(userService);

        User user = mock(User.class);
        Role role = Role.ADMIN;

        when(mockRequest.getParameter("login")).thenReturn("Username");
        when(mockRequest.getParameter("password")).thenReturn("Password");
        when(mockRequest.getSession()).thenReturn(mockSession);
        doReturn(user).when(userService).signIn(anyString(), anyString());
        when(user.getRole()).thenReturn(role);
        doThrow(InvalidUserInformationException.class).when(userService).signIn(anyString(), anyString());
        String result = command.execute(mockRequest, mockResponse);

        assertEquals(result, SIGN_IN_PAGE);
    }


    @Test
    public void execute_validParameters()
            throws ServiceException, CommandException, ServletException, IOException {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpSession mockSession = mock(HttpSession.class);
        UserService userService = mock(UserService.class);
        CommandSignIn command = new CommandSignIn();
        command.setUserService(userService);

        User user = mock(User.class);
        Role role = Role.ADMIN;

        when(mockRequest.getParameter("login")).thenReturn("login");
        when(mockRequest.getParameter("password")).thenReturn("password");
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(userService.signIn(anyString(), anyString())).thenReturn(user);
        when(user.getRole()).thenReturn(role);

        String result = command.execute(mockRequest, mockResponse);

        assertEquals(result, SIGN_IN_PAGE);
    }
}
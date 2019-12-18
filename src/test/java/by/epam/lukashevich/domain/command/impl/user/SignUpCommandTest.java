package by.epam.lukashevich.domain.command.impl.user;

import by.epam.lukashevich.domain.command.exception.CommandException;
import by.epam.lukashevich.domain.entity.user.Role;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.user.InvalidUserInformationException;
import by.epam.lukashevich.domain.service.exception.user.UserServiceException;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.lukashevich.domain.config.BeanFieldJsp.USER_ID;
import static by.epam.lukashevich.domain.config.BeanFieldJsp.USER_ROLE_ID;
import static by.epam.lukashevich.domain.config.JSPPage.SIGN_UP_PAGE_FORWARD;
import static by.epam.lukashevich.domain.config.JSPPage.USER_CABINET_PAGE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SignUpCommandTest {

    @Test
    public void testExecute_signUp_correct()
            throws UserServiceException, CommandException, ServletException, IOException {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpSession mockSession = mock(HttpSession.class);
        UserService userService = mock(UserService.class);
        CommandSignUp command = new CommandSignUp();
        command.setUserService(userService);

        User user = mock(User.class);
        Role role = Role.ADMIN;

        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getParameter("name")).thenReturn("name");
        when(mockRequest.getParameter("email")).thenReturn("email@mail.com");
        when(mockRequest.getParameter("login")).thenReturn("login");
        when(mockRequest.getParameter("password")).thenReturn("password");
        when(mockRequest.getParameter("confirmedPassword")).thenReturn("password");
        when(mockRequest.getParameter("role")).thenReturn("ADMIN");

        doReturn(user).when(userService).signIn(anyString(), anyString());

        mockSession.setAttribute(USER_ID, user.getId());
        mockSession.setAttribute(USER_ROLE_ID, role.getId());

        String result = command.execute(mockRequest, mockResponse);

        assertEquals(result, USER_CABINET_PAGE);
    }

    @Test
    public void testExecute_signUp_badLogin()
            throws UserServiceException, CommandException, ServletException, IOException {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpSession mockSession = mock(HttpSession.class);
        UserService userService = mock(UserService.class);
        CommandSignUp command = new CommandSignUp();
        command.setUserService(userService);

        when(mockRequest.getParameter("login")).thenReturn("1");
        when(mockRequest.getParameter("password")).thenReturn("password");
        when(mockRequest.getParameter("confirmedPassword")).thenReturn("confirmedPassword");
        when(mockRequest.getParameter("name")).thenReturn("name");
        when(mockRequest.getParameter("email")).thenReturn("email");
        when(mockRequest.getParameter("role")).thenReturn("ADMIN");
        when(mockRequest.getSession()).thenReturn(mockSession);

        doThrow(InvalidUserInformationException.class).when(userService).registration(
                anyString(), anyString(), anyString(),
                anyString(), anyString(), anyObject());
        String result = command.execute(mockRequest, mockResponse);

        assertEquals(result, SIGN_UP_PAGE_FORWARD);
    }
}
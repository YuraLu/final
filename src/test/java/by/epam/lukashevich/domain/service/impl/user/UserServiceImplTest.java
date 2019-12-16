package by.epam.lukashevich.domain.service.impl.user;

import by.epam.lukashevich.dao.UserDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.dao.exception.user.UserDAOException;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.exception.user.BannedUserServiceException;
import by.epam.lukashevich.domain.service.exception.user.InvalidUserInformationException;
import by.epam.lukashevich.domain.service.exception.user.UserServiceException;
import by.epam.lukashevich.domain.service.impl.UserServiceImpl;
import by.epam.lukashevich.domain.util.hasher.PasswordHashKeeper;
import by.epam.lukashevich.domain.util.validation.UserValidator;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private final UserServiceImpl service = new UserServiceImpl();
    private final UserValidator validator = mock(UserValidator.class);
    private final PasswordHashKeeper keeper = mock(PasswordHashKeeper.class);
    private final UserDAO userDAO = mock(UserDAO.class);


    @Before
    public void init() {
        service.setKeeper(keeper);
        service.setUserDAO(userDAO);
        service.setValidator(validator);
    }


    @Test(expected = UserServiceException.class)
    public void testGetById_serviceException() throws UserServiceException, DAOException {

        doThrow(UserServiceException.class).when(userDAO).findById(anyInt());
        service.findById(anyInt());
    }


    @Test(expected = UserServiceException.class)
    public void testGetById_valid() throws UserServiceException, DAOException {

        doThrow(UserServiceException.class).when(userDAO).findById(anyInt());
        service.findById(anyInt());
        doThrow(UserServiceException.class).when(userDAO).getByLoginAndPass(anyString(), anyString());
    }


    @Test(expected = InvalidUserInformationException.class)
    public void testSignIn_invalidParameters() throws UserServiceException {
        when(validator.validate(anyString(), anyString())).thenReturn(false);
        service.signIn("Invalid", "Info");
    }

    @Test
    public void testSignIn_valid_params() throws UserDAOException, UserServiceException {
        User user = mock(User.class);
        when(validator.validate(anyString(), anyString())).thenReturn(true);
        doReturn(user).when(userDAO).getByLoginAndPass(anyString(), anyString());
        when(user.getBanned()).thenReturn(false);
        User result = service.signIn("Username", "Password");

        assertEquals(result, user);
    }


    @Test(expected = BannedUserServiceException.class)
    public void testSignIn_bannedUser() throws DAOException, UserServiceException {

        User user = mock(User.class);

        doThrow(BannedUserServiceException.class).when(userDAO).findById(anyInt());
        User result = service.findById(anyInt());

        assertEquals(result, user);
    }
}
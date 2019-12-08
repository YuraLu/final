package by.epam.lukashevich.domain.service.impl.user;

import by.epam.lukashevich.dao.UserDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.dao.exception.user.UserDAOException;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.exception.user.BannedUserServiceException;
import by.epam.lukashevich.domain.service.exception.user.UserServiceException;
import by.epam.lukashevich.domain.service.impl.UserServiceImpl;
import by.epam.lukashevich.domain.util.hasher.PasswordHashKeeper;
import by.epam.lukashevich.domain.util.validation.UserValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class UserServiceImplTest {

    private final UserServiceImpl service = new UserServiceImpl();
    private final UserValidator validator = mock(UserValidator.class);
    private final PasswordHashKeeper keeper = mock(PasswordHashKeeper.class);
    private final UserDAO userDAO = mock(UserDAO.class);


    @BeforeClass
    public void init() {
        service.setKeeper(keeper);
        service.setUserDAO(userDAO);
        service.setValidator(validator);
    }


    @Test(expectedExceptions = UserServiceException.class)
    public void testGetById_serviceException() throws UserServiceException, DAOException {
        //given
        //when
        User user = mock(User.class);
        doThrow(UserServiceException.class).when(userDAO).findById(anyInt());
        service.findById(anyInt());
        //doThrow(UserServiceException.class).when(userRepository).getByLoginAndPass(anyString(), anyString());
        //UserServiceException
    }


    @Test(expectedExceptions = UserServiceException.class)
    public void testGetById_valid() throws UserServiceException, DAOException {
        //given
        //when
        doThrow(UserServiceException.class).when(userDAO).findById(anyInt());
        service.findById(anyInt());
        //doThrow(UserServiceException.class).when(userRepository).getByLoginAndPass(anyString(), anyString());
        //UserServiceException
    }


    @Test(expectedExceptions = UserServiceException.class)
    public void testSignIn_invalidParameters() throws UserServiceException {
        //given
        //when
        when(validator.validate(anyString(), anyString())).thenReturn(false);
        service.signIn("Invalid", "Info");
        //then
        //expecting InvalidUserInformationException
    }

    @Test
    public void testSignIn_valid_params() throws UserDAOException, UserServiceException {
        //given
        User user = mock(User.class);
        //when
        //when
        when(validator.validate(anyString(), anyString())).thenReturn(true);
        doReturn(user).when(userDAO).getByLoginAndPass(anyString(), anyString());
        when(user.getBanned()).thenReturn(false);
        User result = service.signIn("Username", "Password");
        //then
        assertEquals(result, user);
    }


    @Test(expectedExceptions = BannedUserServiceException.class)
    public void testSignIn_bannedUser() throws DAOException, UserServiceException {
        //given
        User user = mock(User.class);
        //when
        doThrow(BannedUserServiceException.class).when(userDAO).findById(anyInt());
        User result = service.findById(anyInt());
        //then
        assertEquals(result, user);
    }
}
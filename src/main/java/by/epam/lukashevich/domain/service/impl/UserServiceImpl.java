package by.epam.lukashevich.domain.service.impl;

import by.epam.lukashevich.dao.UserDAO;
import by.epam.lukashevich.dao.exception.user.InvalidLoginOrPasswordException;
import by.epam.lukashevich.dao.exception.user.UsedEmailException;
import by.epam.lukashevich.dao.exception.user.UsedLoginException;
import by.epam.lukashevich.dao.exception.user.UserDAOException;
import by.epam.lukashevich.dao.factory.DAOFactory;
import by.epam.lukashevich.domain.entity.user.Role;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.user.*;
import by.epam.lukashevich.domain.util.builder.impl.UserBuilderImpl;
import by.epam.lukashevich.domain.util.hasher.PasswordHashKeeper;
import by.epam.lukashevich.domain.util.manager.UtilManager;
import by.epam.lukashevich.domain.util.validation.UserValidator;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
    private PasswordHashKeeper keeper = UtilManager.getInstance().getPasswordHashKeeper();
    private UserValidator validator = UtilManager.getInstance().getUserValidator();


    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setKeeper(PasswordHashKeeper keeper) {
        this.keeper = keeper;
    }

    public void setValidator(UserValidator validator) {
        this.validator = validator;
    }

    @Override
    public List<User> findAll() throws UserServiceException {
        try {
            return userDAO.findAll();
        } catch (UserDAOException e) {
            throw new UserServiceException("No users", e);
        }
    }

    @Override
    public User findById(int id) throws UserServiceException {
        try {
            return userDAO.findById(id);
        } catch (UserDAOException e) {
            throw new UserServiceException("Can't find user", e);
        }
    }

    @Override
    public User signIn(final String login, final String password) throws UserServiceException {
        if (!validator.validate(login, password)) {
            throw new InvalidUserInformationException("Entered information is not valid!");
        }
        final String encodedPass = keeper.generateHash(login, password);

        try {
            final User user = userDAO.getByLoginAndPass(login, encodedPass);
            if (user.getBanned()) {
                throw new BannedUserServiceException("User is banned");
            }

            return user;
        } catch (InvalidLoginOrPasswordException e) {
            throw new InvalidUserInformationException(e);
        } catch (UserDAOException e) {
            throw new UserServiceException("Can't authorize user", e);
        }
    }

    @Override
    public void registration(final String login, final String password, final String confirmedPassword,
                             final String name, final String email, final Role role)
            throws UserServiceException {

        if (validator.isDataEmpty(login, password, confirmedPassword, name, email)) {
            throw new EmptyUserInformationException("One or more fields is empty!");
        }

        if (!validator.validate(login, password, confirmedPassword, name, email)) {
            throw new InvalidUserInformationException("Entered information is not valid!");
        }

        try {
            final String encodedPass = keeper.generateHash(login, password);

            final User user = new UserBuilderImpl()
                    .withLogin(login)
                    .withPassword(encodedPass)
                    .withName(name)
                    .withEmail(email)
                    .withRole(role)
                    .isBanned(false)
                    .build();

            userDAO.add(user);
        } catch (UsedLoginException e) {
            throw new InvalidLoginException("Login is used, try another one!", e);
        } catch (UsedEmailException e) {
            throw new InvalidEmailException("Email is used, try another one!", e);
        } catch (UserDAOException e) {
            throw new UserServiceException("Can't register user", e);
        }
    }

    @Override
    public void updatePassword(int id, String currentPassword,
                               String newPassword, String confirmedPassword) throws UserServiceException {
        if (!validator.validateNewPassword(currentPassword, newPassword, confirmedPassword)) {
            throw new InvalidUserInformationException("Passwords do not match or not valid!");
        }
        try {
            final User user = findById(id);
            final String login = user.getLogin();

            final String encodedCurrentPassword = keeper.generateHash(login, currentPassword);
            final String encodedNewPassword = keeper.generateHash(login, newPassword);

            userDAO.getByLoginAndPass(login, encodedCurrentPassword);

            userDAO.updatePassword(id, encodedNewPassword);

        } catch (InvalidLoginOrPasswordException e) {
            throw new InvalidPasswordException(e);
        } catch (UserDAOException e) {
            throw new UserServiceException("Can't update user", e);
        }
    }

    @Override
    public void update(final int id, final String name, final String email) throws UserServiceException {
        if (!validator.validateName(name)) {
            throw new InvalidLoginException("Can`t edit user! Name is not valid!");
        }

        if (!validator.validateEmail(email)) {
            throw new InvalidEmailException("Can`t edit user! Email is not valid!");
        }

        try {
            final User user = new UserBuilderImpl(id)
                    .withName(name)
                    .withEmail(email)
                    .build();
            userDAO.update(user);
        } catch (UserDAOException e) {
            throw new UserServiceException("Can't update user", e);
        }
    }

    @Override
    public void updateBanStatus(final int id) throws UserServiceException {
        try {
            userDAO.updateBanStatus(id);
        } catch (UserDAOException e) {
            throw new UserServiceException("Can't update user ban status", e);
        }
    }

    @Override
    public void updateStatus(final int id) throws UserServiceException {
        try {
            userDAO.updateStatus(id);
        } catch (UserDAOException e) {
            throw new UserServiceException("Can't update user status", e);
        }
    }

    @Override
    public void delete(int id) throws UserServiceException {
        try {
            userDAO.delete(id);
        } catch (UserDAOException e) {
            throw new UserServiceException("Can't delete user", e);
        }
    }
}
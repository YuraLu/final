package by.epam.lukashevich.domain.service.impl;

import by.epam.lukashevich.dao.DAOFactory;
import by.epam.lukashevich.dao.UserDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.entity.user.Role;
import by.epam.lukashevich.domain.service.UserService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    public UserServiceImpl() {
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("No users", e);
        }
    }

    @Override
    public User findById(int id) throws ServiceException {
        try {
            return userDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't find user", e);
        }
    }

    @Override
    public User authorization(String login, String password) throws ServiceException {
        User user;
        try {
            user = userDAO.authorization(login, password);
        } catch (DAOException e) {
            throw new ServiceException("Can't authorize user", e);
        }
        return user;
    }

    @Override
    public void registration(String login, String password, String email, String name, Role role) throws ServiceException {
        try {
            userDAO.registration(login, password, email, name, role);
        } catch (DAOException e) {
            throw new ServiceException("Can't register user", e);
        }
    }

    @Override
    public void update(User user, String newPassword) throws ServiceException {
        try {
            userDAO.updateUserPassword(user.getLogin(), newPassword);
        } catch (DAOException e) {
            throw new ServiceException("Can't update user", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            userDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't delete user", e);
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        try {
           userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException("Can't update user", e);
        }
    }

    @Override
    public void updateBanStatus(int id) throws ServiceException {
        try {
            userDAO.updateBanStatus(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't update user ban status", e);
        }
    }

    @Override
    public void updateStatus(int id) throws ServiceException {
        try {
            userDAO.updateBanStatus(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't update user status", e);
        }
    }
}

package by.epam.trjava.tutorsystem.service.impl;

import by.epam.trjava.tutorsystem.dao.DAOFactory;
import by.epam.trjava.tutorsystem.dao.UserDAO;
import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.entity.User;
import by.epam.trjava.tutorsystem.service.UserService;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDAO userDao = DAOFactory.getInstance().getUserDAO();

    public UserServiceImpl() {
    }

    @Override
    public User authorization(String login, String password) throws ServiceException {
        User user;
        try {
            user = userDao.authorization(login, password);
        } catch (DAOException e) {
            throw new ServiceException("Can't authorize user", e);
        }
        return user;
    }

    @Override
    public boolean registration(User user) throws ServiceException {
        return false;
    }

    @Override
    public User findUser(int id) throws ServiceException {
        try {
            return userDao.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't find user", e);
        }
    }

    @Override
    public void deleteUser(String id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't delete user", e);
        }
    }

    @Override
    public void updateUser(User user, String newPassword) throws ServiceException {
        try {
            userDao.updateUserPassword(user.getLogin(), newPassword);
        } catch (DAOException e) {
            throw new ServiceException("Can't update user", e);
        }
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DAOException e) {
            throw new ServiceException("No users", e);
        }
    }
}

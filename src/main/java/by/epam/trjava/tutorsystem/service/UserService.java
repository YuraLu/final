package by.epam.trjava.tutorsystem.service;

import by.epam.trjava.tutorsystem.entity.User;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User findUser(int id) throws ServiceException;

    void deleteUser(int id) throws ServiceException;

    void updateUser(User user, String newPassword) throws ServiceException;

    List<User> findAllUsers() throws ServiceException;

    User authorization(String login, String password) throws ServiceException;

    boolean registration(String login, String password, String email, String name, int roleId)  throws ServiceException;
}

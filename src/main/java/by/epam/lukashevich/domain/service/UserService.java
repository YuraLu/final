package by.epam.lukashevich.domain.service;

import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.entity.user.Role;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public interface UserService {

    List<User> findAll() throws ServiceException;

    User findById(int id) throws ServiceException;

    User authorization(String login, String password) throws ServiceException;

    void registration(String login, String password, String email, String name, Role role) throws ServiceException;

    void update(User user, String newPassword) throws ServiceException;

    void delete(int id) throws ServiceException;

    void update(User user) throws ServiceException;

    void updateBanStatus(int id) throws ServiceException;

    void updateStatus(int id) throws ServiceException;
}

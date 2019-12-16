package by.epam.lukashevich.domain.service;

import by.epam.lukashevich.domain.entity.user.Role;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.service.exception.user.UserServiceException;

import java.util.List;

public interface UserService {

    List<User> findAll() throws UserServiceException;

    User findById(int id) throws UserServiceException;

    User signIn(String login, String password) throws UserServiceException;

    void registration(String login, String password, String confirmedPassword, String name, String email, Role role)
            throws UserServiceException;

    void update(int id, String name, String email) throws UserServiceException;

    void updatePassword(int id, String currentPassword, String newPassword, String confirmedPassword)
            throws UserServiceException;

    void updateBanStatus(int id) throws UserServiceException;

    void updateStatus(int id) throws UserServiceException;

    void delete(int id) throws UserServiceException;
}
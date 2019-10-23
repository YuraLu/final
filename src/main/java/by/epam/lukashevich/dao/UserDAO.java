package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.entity.user.UserRole;

import java.util.List;

public interface UserDAO {

    List<User> findAll() throws DAOException;

    User findById(int userId) throws DAOException;

    User authorization(String login, String password) throws DAOException;

    boolean registration(String userLogin, String userPassword, String userEmail, String userName, UserRole role) throws DAOException;

    boolean updateUserPassword(String login, String newPassword) throws DAOException;

    boolean delete(int userId) throws DAOException;

    boolean update(User user) throws DAOException;

    void updateBanStatus(int id) throws DAOException;
}

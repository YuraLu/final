package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.entity.user.Role;

import java.util.List;

public interface UserDAO {

    List<User> findAll() throws DAOException;

    User findById(int id) throws DAOException;

    User authorization(String login, String password) throws DAOException;

    void registration(String login, String password, String email, String name, Role role) throws DAOException;

    boolean updateUserPassword(String login, String newPassword) throws DAOException;

    boolean delete(int id) throws DAOException;

    boolean update(User user) throws DAOException;

    void updateBanStatus(int id) throws DAOException;

    void updateStatus(int id) throws DAOException;
}

package by.epam.trjava.tutorsystem.dao;

import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.entity.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll() throws DAOException;

    User findById(int userId) throws DAOException;

    User authorization(String login, String password) throws DAOException;

    boolean registration(String userLogin, String userPassword, String userEmail, String userName, int userRoleId) throws DAOException;

    boolean updateUserPassword(String login, String newPassword) throws DAOException;

    boolean delete(int userId) throws DAOException;
}

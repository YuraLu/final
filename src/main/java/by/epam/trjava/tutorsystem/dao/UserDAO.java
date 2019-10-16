package by.epam.trjava.tutorsystem.dao;

import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.entity.User;

import java.util.List;

public interface UserDAO {

    User authorization(String login, String password) throws DAOException;

    boolean registration(String userLogin, String userPassword, String userEmail, String userName) throws DAOException;

    User findById(int userId) throws DAOException;

    User findUserByEmail(String email) throws DAOException;

    boolean delete(String userId) throws DAOException;

    boolean updateUserPassword(String login, String newPassword) throws DAOException;

    List<User> findAll() throws DAOException;

}

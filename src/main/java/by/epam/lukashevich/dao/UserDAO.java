package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.user.User;

import java.util.List;

public interface UserDAO extends CommonDAO<User> {

    @Override
    List<User> findAll() throws DAOException;

    @Override
    User findById(Integer id) throws DAOException;

    @Override
    boolean add(User user) throws DAOException;

    @Override
    boolean update(User user) throws DAOException;

    @Override
    boolean delete(Integer id) throws DAOException;




    void updateBanStatus(Integer id) throws DAOException;

    void updateStatus(Integer id) throws DAOException;

    User authorization(String login, String password) throws DAOException;

    boolean updateUserPassword(String login, String newPassword) throws DAOException;
}

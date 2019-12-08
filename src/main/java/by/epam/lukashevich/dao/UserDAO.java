package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.exception.user.UserDAOException;
import by.epam.lukashevich.domain.entity.user.User;

import java.util.List;

public interface UserDAO extends CommonDAO<User> {

    @Override
    List<User> findAll() throws UserDAOException;

    @Override
    User findById(Integer id) throws UserDAOException;

    @Override
    boolean add(User user) throws UserDAOException;

    @Override
    boolean update(User user) throws UserDAOException;

    @Override
    boolean delete(Integer id) throws UserDAOException;

    void updateBanStatus(Integer id) throws UserDAOException;

    void updateStatus(Integer id) throws UserDAOException;

    User getByLoginAndPass(String login, String encodedPass) throws UserDAOException;

    void updatePassword(int id, String newEncodedPass)throws UserDAOException;

    boolean isLoginUsed(String login) throws UserDAOException;
}
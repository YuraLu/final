package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.impl.SQLUserDAOImpl;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new SQLUserDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}

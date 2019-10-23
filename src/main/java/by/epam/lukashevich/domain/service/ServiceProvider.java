package by.epam.lukashevich.domain.service;


import by.epam.lukashevich.domain.service.impl.UserServiceImpl;

public class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    public static ServiceProvider getInstance() {
        return instance;
    }

    private ServiceProvider() {
    }

    private final UserService userService = new UserServiceImpl();

    public UserService getUserService() {
        return userService;
    }

}

package by.epam.lukashevich.domain.util.manager;

import by.epam.lukashevich.domain.util.hasher.PasswordHashKeeper;
import by.epam.lukashevich.domain.util.hasher.impl.PasswordHashKeeperImpl;
import by.epam.lukashevich.domain.util.validation.UserValidator;
import by.epam.lukashevich.domain.util.validation.impl.UserValidatorImpl;

public final class UtilManager {

    private static final UtilManager instance = new UtilManager();
    private final PasswordHashKeeper passwordHashKeeper = new PasswordHashKeeperImpl();
    private final UserValidator userValidator = new UserValidatorImpl();

    private UtilManager() {
    }

    public static UtilManager getInstance() {
        return instance;
    }

    public PasswordHashKeeper getPasswordHashKeeper() {
        return passwordHashKeeper;
    }

    public UserValidator getUserValidator() {
        return userValidator;
    }
}

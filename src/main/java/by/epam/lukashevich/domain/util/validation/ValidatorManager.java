package by.epam.lukashevich.domain.util.validation;


import by.epam.lukashevich.domain.util.validation.impl.UserValidatorImpl;

public final class ValidatorManager {

    private static final ValidatorManager instance = new ValidatorManager();

    public static ValidatorManager getInstance() {
        return instance;
    }

    private ValidatorManager() {

    }

    private final UserValidator userValidator = new UserValidatorImpl();

    public UserValidator getUserValidator() {
        return userValidator;
    }
}

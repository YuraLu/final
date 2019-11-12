package by.epam.lukashevich.domain.util.validation.impl;

import by.epam.lukashevich.domain.util.validation.UserValidator;

public class UserValidatorImpl implements UserValidator {
    private static final String LOGIN_PATTERN = "^[a-zA-Z][a-zA-Z0-9-_.]{2,16}$";

    private static final String PASSWORD_PATTERN = "^[a-zA-Z][a-zA-Z0-9-_.]{4,16}$";

    private static final String NAME_PATTERN = ".{2,30}";

    /**
     * Validates given parameters by required formats( checks if login consists of 2-16 symbols
     * and password consists 4-16 symbols).
     *
     * @param login    login to validate
     * @param password password to validate
     * @return {@code true} if given parameters correct
     * {@code false} otherwise.
     */


    @Override
    public boolean validate(final String login, final String password) {
        return login.matches(LOGIN_PATTERN)
                && password.matches(PASSWORD_PATTERN);
    }

    @Override
    public boolean validate(String login, String password,
                            String name, String email) {
        return validate(login, password)
                && name.matches(NAME_PATTERN);
    }
}

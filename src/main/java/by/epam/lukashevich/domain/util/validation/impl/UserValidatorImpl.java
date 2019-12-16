package by.epam.lukashevich.domain.util.validation.impl;

import by.epam.lukashevich.domain.util.validation.UserValidator;

public class UserValidatorImpl implements UserValidator {
    private static final String LOGIN_PATTERN = "^[a-zA-Z][a-zA-Z0-9-_.]{3,16}$";

    private static final String PASSWORD_PATTERN = "^[a-zA-Z][a-zA-Z0-9-_.]{6,16}$";
    private static final String NAME_PATTERN = ".{2,20}";
    private static final String EMAIL_PATTERN = "[a-z][[a-z][0-9][-][_]]{3,15}[@][a-z]{2,10}[.][a-z]{2,4}";

    /**
     * Validates given parameters by required formats( checks if login consists of 3-16 symbols
     * and password consists 6-16 symbols).
     *
     * @param login    login to validate
     * @param password password to validate
     * @return {@code true} if given parameters correct
     * {@code false} otherwise.
     * @author Yuri Lukashevich
     * @since JDK1.0
     */
    @Override
    public boolean validate(final String login, final String password) {
        if (!login.isEmpty() && !password.isEmpty()) {

            return login.matches(LOGIN_PATTERN)
                    && password.matches(PASSWORD_PATTERN);
        }
        return false;
    }

    @Override
    public boolean validateNameEmail(String name, String email) {

        return name.matches(NAME_PATTERN)
                && email.matches(EMAIL_PATTERN);

    }

    @Override
    public boolean validateName(String name) {
        return name.matches(NAME_PATTERN);
    }

    @Override
    public boolean validateEmail(String email) {
        return email.matches(EMAIL_PATTERN);
    }


    @Override
    public boolean validate(String login, String password, String confirmedPassword, String name, String email) {

        return password.equals(confirmedPassword)
                && validate(login, password)
                && name.matches(NAME_PATTERN)
                && email.matches(EMAIL_PATTERN);

    }

    @Override
    public boolean validatePassword(String password, String confirmedPassword) {

        return password.equals(confirmedPassword)
                && password.matches(PASSWORD_PATTERN)
                && confirmedPassword.matches(PASSWORD_PATTERN);

    }

    @Override
    public boolean validateNewPassword(String currentPassword, String newPassword, String confirmedPassword) {

        return newPassword.equals(confirmedPassword)
                && currentPassword.matches(PASSWORD_PATTERN)
                && newPassword.matches(PASSWORD_PATTERN)
                && confirmedPassword.matches(PASSWORD_PATTERN);

    }

    @Override
    public boolean isDataEmpty(String login, String password, String confirmedPassword, String name, String email) {

        return login.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty()
                || name.isEmpty() || email.isEmpty();
    }
}
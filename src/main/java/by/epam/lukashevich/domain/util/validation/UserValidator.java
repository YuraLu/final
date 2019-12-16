package by.epam.lukashevich.domain.util.validation;

public interface UserValidator {

    boolean validate(final String login, final String password);

    boolean validateName(String name);

    boolean validateEmail(String email);

    boolean validate(final String login, final String password, final String confirmedPassword,
                     final String name, final String email);

    boolean validateNameEmail(final String name, final String email);

    boolean validatePassword(String password, String confirmedPassword);

    boolean validateNewPassword(String currentPassword, String newPassword, String confirmedPassword);

    boolean isDataEmpty(String login, String password, String confirmedPassword, String name, String email);
}
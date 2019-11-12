package by.epam.lukashevich.domain.util.validation;

public interface UserValidator {

    boolean validate(final String login, final String password);

    boolean validate(final String login, final String password,
                     final String name, final String email);

}


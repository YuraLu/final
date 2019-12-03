package by.epam.lukashevich.domain.util.hasher;

public interface PasswordHashKeeper {
    String generateHash(String login, String password);
}

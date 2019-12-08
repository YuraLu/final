package by.epam.lukashevich.dao.exception.user;

public class UsedEmailException extends UserDAOException {

    public UsedEmailException() {
        super();
    }

    public UsedEmailException(String message) {
        super(message);
    }

    public UsedEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsedEmailException(Throwable cause) {
        super(cause);
    }
}
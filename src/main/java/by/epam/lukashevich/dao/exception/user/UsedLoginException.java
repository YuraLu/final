package by.epam.lukashevich.dao.exception.user;


public class UsedLoginException extends UserDAOException {

    public UsedLoginException() {
        super();
    }

    public UsedLoginException(String message) {
        super(message);
    }

    public UsedLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsedLoginException(Throwable cause) {
        super(cause);
    }
}
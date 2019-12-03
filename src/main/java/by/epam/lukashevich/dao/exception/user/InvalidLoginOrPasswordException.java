package by.epam.lukashevich.dao.exception.user;

public class InvalidLoginOrPasswordException extends UserDAOException {

    public InvalidLoginOrPasswordException() {
        super();
    }

    public InvalidLoginOrPasswordException(String message) {
        super(message);
    }

    public InvalidLoginOrPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidLoginOrPasswordException(Throwable cause) {
        super(cause);
    }
}

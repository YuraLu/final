package by.epam.lukashevich.domain.service.exception.user;

public class InvalidUserInformationException extends UserServiceException {

    public InvalidUserInformationException() {
        super();
    }

    public InvalidUserInformationException(String message) {
        super(message);
    }

    public InvalidUserInformationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserInformationException(Throwable cause) {
        super(cause);
    }
}

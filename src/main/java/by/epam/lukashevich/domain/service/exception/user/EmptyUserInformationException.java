package by.epam.lukashevich.domain.service.exception.user;

public class EmptyUserInformationException extends UserServiceException {
    public EmptyUserInformationException() {
    }

    public EmptyUserInformationException(String message) {
        super(message);
    }

    public EmptyUserInformationException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyUserInformationException(Throwable cause) {
        super(cause);
    }
}

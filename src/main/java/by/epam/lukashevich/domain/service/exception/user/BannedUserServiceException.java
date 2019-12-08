package by.epam.lukashevich.domain.service.exception.user;

public class BannedUserServiceException extends UserServiceException {

    public BannedUserServiceException() {
        super();
    }

    public BannedUserServiceException(String message) {
        super(message);
    }

    public BannedUserServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BannedUserServiceException(Throwable cause) {
        super(cause);
    }
}
package by.epam.lukashevich.domain.service.exception.user;

import by.epam.lukashevich.domain.service.exception.ServiceException;

public class UserServiceException extends ServiceException {

    public UserServiceException() {
        super();
    }

    public UserServiceException(String message) {
        super(message);
    }

    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserServiceException(Throwable cause) {
        super(cause);
    }
}
package by.epam.lukashevich.domain.util.hasher.exception;

public class PasswordHashKeeperRuntimeException extends RuntimeException {

    public PasswordHashKeeperRuntimeException() {
        super();
    }

    public PasswordHashKeeperRuntimeException(String message) {
        super(message);
    }

    public PasswordHashKeeperRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordHashKeeperRuntimeException(Throwable cause) {
        super(cause);
    }
}
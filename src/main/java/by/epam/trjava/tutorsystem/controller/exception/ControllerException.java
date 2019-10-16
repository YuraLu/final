package by.epam.trjava.tutorsystem.controller.exception;

public class ControllerException extends Exception {
    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}

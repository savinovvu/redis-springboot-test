package ru.inbox.savinov_vu.app.exception.hierarchy;

public abstract class AppException extends RuntimeException {


    public AppException(String message, Throwable cause) {
        super(message, cause);
    }


    public AppException(String message) {
        super(message);
    }


    public AppException(Throwable throwable) {
        super(throwable);
    }
}

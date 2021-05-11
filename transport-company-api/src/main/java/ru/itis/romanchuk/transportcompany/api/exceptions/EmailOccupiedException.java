package ru.itis.romanchuk.transportcompany.api.exceptions;

public class EmailOccupiedException extends Exception{
    public EmailOccupiedException() {
    }

    public EmailOccupiedException(String message) {
        super(message);
    }

    public EmailOccupiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailOccupiedException(Throwable cause) {
        super(cause);
    }
}

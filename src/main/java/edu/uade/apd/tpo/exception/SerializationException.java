package edu.uade.apd.tpo.exception;

public class SerializationException extends RuntimeException {

    public SerializationException(String message, Throwable t) {
        super(message, t);
    }
}

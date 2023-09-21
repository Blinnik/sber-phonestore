package com.sber.phonestore.exception;

/** An exception that is thrown if the object was not found */
public class NotFoundException extends RuntimeException {

    /**
     * An exception that is thrown if the object was not found
     *
     * @param message A message clarifying what exactly was not found
     */
    public NotFoundException(final String message) {
        super(message);
    }
}

package com.sber.phonestore.handler;

import com.sber.phonestore.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * An application error handler that catches errors, turns them
 * into JSON and sends them to the client with the specified status code
 */
@RestControllerAdvice
public class ErrorHandler {

    /**
     * Catches errors of the NotFoundException type
     *
     * @param e The type of error being caught. In this case, the parent class of NotFoundException
     * @return ErrorResponse consisting of the error class name, message, and status code
     * @see com.sber.phonestore.exception.NotFoundException
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundExceptions(final RuntimeException e) {
        return new ErrorResponse(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Catches errors of the BadRequestException type
     *
     * @param e The type of error being caught. In this case, the parent class
     *          of MethodArgumentNotValidException and MethodArgumentTypeMismatchException
     * @return ErrorResponse consisting of the error class name, message, and status code
     */
    @ExceptionHandler({MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestExceptions(final RuntimeException e) {
        return new ErrorResponse(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /** A class that represents the response in case of an error */
    @AllArgsConstructor
    static class ErrorResponse {

        /** Error class name */
        String error;

        /** Error message. Specifies additional information about the error */
        String errorMessage;

        /** Error code status */
        HttpStatus responseCodeStatus;

        public String getError() {
            return error;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public HttpStatus getResponseCodeStatus() {
            return responseCodeStatus;
        }
    }
}

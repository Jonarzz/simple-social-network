package app.social.network.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandlingController {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EntityExistsException.class)
    public void handleConflict() {}

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public void handleNotFound() {}

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Value not allowed, should be one of: male, female, other")
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgument() {}

}

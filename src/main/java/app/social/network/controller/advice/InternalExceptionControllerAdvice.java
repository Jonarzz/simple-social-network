package app.social.network.controller.advice;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InternalExceptionControllerAdvice {

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Resource already exists")
    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(ConstraintViolationException ex) {

    }

}

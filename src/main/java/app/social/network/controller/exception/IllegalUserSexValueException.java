package app.social.network.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal user sex value")
public class IllegalUserSexValueException extends RuntimeException {

    private static final long serialVersionUID = 1L;

}

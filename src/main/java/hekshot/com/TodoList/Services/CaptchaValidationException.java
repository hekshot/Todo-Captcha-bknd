package hekshot.com.TodoList.Services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CaptchaValidationException extends RuntimeException {
    public CaptchaValidationException(String message) {
        super(message);
    }
}

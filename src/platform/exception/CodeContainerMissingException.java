package platform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CodeContainerMissingException extends RuntimeException {
    public CodeContainerMissingException(String message) {
        super(message);
    }
}

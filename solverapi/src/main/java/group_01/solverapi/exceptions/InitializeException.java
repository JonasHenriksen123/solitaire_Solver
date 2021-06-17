package group_01.solverapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Service was unable to initialize the game")
public class InitializeException extends RuntimeException {

}

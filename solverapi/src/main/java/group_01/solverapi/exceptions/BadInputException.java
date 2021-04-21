package group_01.solverapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Fatal error when decoding input")
public class BadInputException extends RuntimeException{
}

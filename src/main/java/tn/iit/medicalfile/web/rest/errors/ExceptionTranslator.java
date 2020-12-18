package tn.iit.medicalfile.web.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorSM> processRessourceNotFound(ResourceNotFoundException exception){
        ResponseEntity.BodyBuilder builder = ResponseEntity.status (HttpStatus.NOT_FOUND);
        return builder.body (new ErrorSM ("NOT FOUND", exception));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorSM> processValidationError(MethodArgumentNotValidException exception){
        ResponseEntity.BodyBuilder builder = ResponseEntity.status (HttpStatus.BAD_REQUEST);
        return builder.body (new ErrorSM ("VALIDATION ERROR", exception));
    }
}

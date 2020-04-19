package br.com.bills.config;

import br.com.bills.exception.NotFoundException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomError {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    private ErrorMessage onNotFound(NotFoundException e) {
        return new ErrorMessage(e.getMessage(), Collections.emptyList());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ErrorMessage onMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        return new ErrorMessage("", e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ConstraintError(fieldError.getField(),
                        messageSource.getMessage(fieldError.getDefaultMessage(),
                                new Object[]{},
                                fieldError.getDefaultMessage(),
                                LocaleContextHolder.getLocale())))
                .collect(Collectors.toList())
        );
    }
}

@Data
@AllArgsConstructor
class ErrorMessage {

    @JsonProperty("message")
    private String message;

    @JsonProperty("constraintErrors")
    private List<ConstraintError> constraintErrors;
}

@Data
@AllArgsConstructor
class ConstraintError {

    @JsonProperty("field")
    private String field;

    @JsonProperty("message")
    private String message;
}

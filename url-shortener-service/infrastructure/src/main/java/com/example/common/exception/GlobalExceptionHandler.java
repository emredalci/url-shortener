package com.example.common.exception;

import com.example.adapters.url.exception.UrlNotFoundException;
import com.example.adapters.url.exception.UrlNotValidException;
import com.example.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError)error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        log.debug("Bad request! Message : ", ex);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UrlNotValidException.class)
    public ResponseEntity<String> handle(UrlNotValidException ex, Locale locale){
        log.debug("Url not valid. Message : {}", ex.getMessage());
        List<String> requiredFieldErrorMessages = retrieveLocalizationMessage(ex.getKey(), locale);
        return new ResponseEntity<>(requiredFieldErrorMessages.get(1), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<String> handle(UrlNotFoundException ex, Locale locale){
        log.error("Url not found. Message : {}", ex.getMessage());
        List<String> requiredFieldErrorMessages = retrieveLocalizationMessage(ex.getKey(), locale);
        return new ResponseEntity<>(requiredFieldErrorMessages.get(1), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handle(UserNotFoundException ex, Locale locale){
        log.error("User not found. Message : {}", ex.getMessage());
        List<String> requiredFieldErrorMessages = retrieveLocalizationMessage(ex.getKey(), locale);
        return new ResponseEntity<>(requiredFieldErrorMessages.get(1), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handle(DataIntegrityViolationException ex, Locale locale){
        log.error("Url is already exist. Message : {}", ex.getMessage());
        List<String> requiredFieldErrorMessages = retrieveLocalizationMessage("url.duplicate.error", locale);
        return new ResponseEntity<>(requiredFieldErrorMessages.get(1), HttpStatus.NOT_FOUND);
    }

    private List<String> retrieveLocalizationMessage(String key, Locale locale, String... args) {
        String message = messageSource.getMessage(key, args, locale);
        return Pattern.compile(",").splitAsStream(message).toList();
    }

}

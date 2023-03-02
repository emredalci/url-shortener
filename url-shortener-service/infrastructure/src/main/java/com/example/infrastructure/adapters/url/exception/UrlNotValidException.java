package com.example.infrastructure.adapters.url.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UrlNotValidException extends RuntimeException{

    public UrlNotValidException(String message) {
        super(message);
    }
}

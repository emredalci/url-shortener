package com.example.adapters.url.exception;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UrlNotValidException extends RuntimeException{

    private final String key;

    public UrlNotValidException(String key) {
        this.key = key;
    }
}

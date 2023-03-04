package com.example.adapters.url.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlNotFoundException extends RuntimeException{

    private final String key;

    public UrlNotFoundException(String key) {
        this.key=key;
    }
}

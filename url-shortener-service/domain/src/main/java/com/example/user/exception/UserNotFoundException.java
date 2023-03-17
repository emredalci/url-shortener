package com.example.user.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends RuntimeException{

    private final String key;

    public UserNotFoundException(String key) {
        super(key);
        this.key=key;
    }
}

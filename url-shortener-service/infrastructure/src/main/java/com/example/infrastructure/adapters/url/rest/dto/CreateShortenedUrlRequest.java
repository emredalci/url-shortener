package com.example.infrastructure.adapters.url.rest.dto;

import com.example.infrastructure.adapters.url.exception.UrlNotValidException;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.validator.routines.UrlValidator;

public record CreateShortenedUrlRequest(@NotBlank String url, long userId) {

    public CreateShortenedUrlRequest(String url, long userId){
        this.userId = userId;

        boolean valid = UrlValidator.getInstance().isValid(url);
        if (valid) this.url = url;
        throw new UrlNotValidException("Error");
    }

}

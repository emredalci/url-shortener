package com.example.adapters.url.rest.dto;

import com.example.adapters.url.exception.UrlNotValidException;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.validator.routines.UrlValidator;

public record CreateShortenedUrlRequest(@NotBlank String url) {

    public CreateShortenedUrlRequest(String url){
        boolean valid = UrlValidator.getInstance().isValid(url);
        if (valid) this.url = url;
        throw new UrlNotValidException("Error");
    }

}

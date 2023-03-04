package com.example.adapters.url.rest.dto;

import com.example.adapters.url.exception.UrlNotValidException;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.validator.routines.UrlValidator;

public record CreateShortenedUrlRequest(@NotBlank @JsonProperty("url") String url) {

    public void validateUrl(){
        boolean valid = UrlValidator.getInstance().isValid(url);
        if (valid) return;
        throw new UrlNotValidException("url.request.error");
    }

}

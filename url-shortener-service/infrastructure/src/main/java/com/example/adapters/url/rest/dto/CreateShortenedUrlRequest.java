package com.example.adapters.url.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record CreateShortenedUrlRequest(@NotBlank @JsonProperty("url") String url) {


}

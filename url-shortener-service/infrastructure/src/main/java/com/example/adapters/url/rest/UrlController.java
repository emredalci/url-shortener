package com.example.adapters.url.rest;

import com.example.adapters.url.rest.dto.CreateShortenedUrlRequest;
import com.example.common.usecase.UseCaseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.url.usecase.CreateShortenedUrl;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UseCaseHandler<String, CreateShortenedUrl> createShortenedUrlUseCaseHandler;

    @PostMapping("/user/{userId}/url/create")
    public ResponseEntity<String> create(@PathVariable Long userId, @RequestBody @Valid CreateShortenedUrlRequest urlRequest) {
        String shortenedUrl = createShortenedUrlUseCaseHandler.handler(CreateShortenedUrl.builder()
                .userId(userId)
                .url(urlRequest.url())
                .build());
        return ResponseEntity.ok(shortenedUrl);
    }
}

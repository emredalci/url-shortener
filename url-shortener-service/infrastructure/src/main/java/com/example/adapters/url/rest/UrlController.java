package com.example.adapters.url.rest;

import com.example.adapters.url.rest.dto.CreateShortenedUrlRequest;
import com.example.common.usecase.UseCaseHandler;
import com.example.url.usecase.Redirect;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.url.usecase.CreateShortenedUrl;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UrlController {

    private final UseCaseHandler<String, CreateShortenedUrl> createShortenedUrlUseCaseHandler;

    private final UseCaseHandler<String, Redirect> redirectUseCaseHandler;

    @PostMapping("/user/{userId}/url/create")
    public ResponseEntity<String> create(@PathVariable Long userId, @RequestBody @Valid CreateShortenedUrlRequest urlRequest) {
        String shortenedUrl = createShortenedUrlUseCaseHandler.handler(new CreateShortenedUrl(userId, urlRequest.url()));
        return ResponseEntity.ok(shortenedUrl);
    }

    @GetMapping("/{shortened}")
    public ResponseEntity<Void> redirect(@PathVariable String shortened) {
        String url = redirectUseCaseHandler.handler(Redirect.builder().shortened(shortened).build());
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(URI.create(url)).build();
    }
}

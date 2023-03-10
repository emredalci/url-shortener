package com.example.unit.url;

import com.example.adapters.url.rest.UrlController;
import com.example.adapters.url.rest.dto.CreateShortenedUrlRequest;
import com.example.common.usecase.UseCaseHandler;
import com.example.url.usecase.CreateShortenedUrl;
import com.example.url.usecase.CreateShortenedUrlUseCaseHandler;
import com.example.url.usecase.Redirect;
import com.example.url.usecase.RedirectUseCaseHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UrlControllerTest {

    private final UseCaseHandler<String, CreateShortenedUrl> createShortenedUrlUseCaseHandler = Mockito.mock(CreateShortenedUrlUseCaseHandler.class);
    private final UseCaseHandler<String, Redirect> redirectUseCaseHandler = Mockito.mock(RedirectUseCaseHandler.class);
    private final UrlController urlController = new UrlController(createShortenedUrlUseCaseHandler, redirectUseCaseHandler);

    @Test
    void Should_ReturnShortenedValue() {
        //GIVEN
        long userId = 1;
        CreateShortenedUrlRequest request = new CreateShortenedUrlRequest("http://www.google.com");
        String expected = "WN6jeG";
        Mockito.when(createShortenedUrlUseCaseHandler.handler(Mockito.any(CreateShortenedUrl.class))).thenReturn(expected);
        //WHEN
        ResponseEntity<String> response = urlController.create(userId, request);
        //THEN
        Assertions.assertAll(
                () -> Assertions.assertTrue(response.getStatusCode().is2xxSuccessful()),
                () -> Assertions.assertEquals(expected, response.getBody())
        );
    }
}

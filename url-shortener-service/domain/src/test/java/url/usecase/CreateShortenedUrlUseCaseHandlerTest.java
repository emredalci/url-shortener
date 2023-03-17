package url.usecase;


import com.example.url.usecase.CreateShortenedUrl;
import com.example.url.usecase.CreateShortenedUrlUseCaseHandler;
import com.example.user.exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import url.port.FakeUrlPort;
import url.port.FakeUserPort;
import com.example.url.port.UrlPort;
import com.example.user.port.UserPort;

class CreateShortenedUrlUseCaseHandlerTest {

    private CreateShortenedUrlUseCaseHandler useCaseHandler;

    @BeforeEach
    void setUp(){
        UserPort userPort = new FakeUserPort();
        UrlPort urlPort = new FakeUrlPort();
        useCaseHandler = new CreateShortenedUrlUseCaseHandler(userPort, urlPort);
    }

    @Test
    void Should_ReturnShortened_When_UserExist(){
        //GIVEN
        CreateShortenedUrl useCase = CreateShortenedUrl.builder()
                .userId(1)
                .url("http://google.com")
                .build();
        //WHEN
        String result = useCaseHandler.handler(useCase);
        //THEN
        Assertions.assertFalse(result.isBlank());
    }

    @Test
    void Should_ThrowException_When_UserNotExist(){
        //GIVEN
        CreateShortenedUrl useCase = CreateShortenedUrl.builder()
                .userId(2)
                .url("http://google.com")
                .build();
        String expectedErrorMessage = "user.not.found.error";
        //WHEN
        //THEN
        UserNotFoundException exception = Assertions.assertThrows(UserNotFoundException.class, () -> useCaseHandler.handler(useCase));
        Assertions.assertEquals(exception.getMessage(), expectedErrorMessage);
    }

}

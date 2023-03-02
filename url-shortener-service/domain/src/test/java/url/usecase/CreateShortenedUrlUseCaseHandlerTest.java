package url.usecase;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import url.port.FakeUrlPort;
import url.port.FakeUserPort;
import url.port.UrlPort;
import user.port.UserPort;

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
        String expectedErrorMessage = "Error";
        //WHEN
        //THEN
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> useCaseHandler.handler(useCase));
        Assertions.assertEquals(exception.getMessage(), expectedErrorMessage);
    }

}

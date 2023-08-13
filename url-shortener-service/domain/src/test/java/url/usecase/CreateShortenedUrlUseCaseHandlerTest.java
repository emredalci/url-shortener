package url.usecase;


import com.example.common.exception.BusinessException;
import com.example.url.usecase.CreateShortenedUrl;
import com.example.url.usecase.CreateShortenedUrlUseCaseHandler;
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
        CreateShortenedUrl useCase = new CreateShortenedUrl(1, "http://google.com");
        //WHEN
        String result = useCaseHandler.handler(useCase);
        //THEN
        Assertions.assertFalse(result.isBlank());
    }

    @Test
    void Should_ThrowException_When_UserNotExist(){
        //GIVEN
        CreateShortenedUrl useCase = new CreateShortenedUrl(2, "http://google.com");
        String expectedErrorMessage = "user.not.found.error";
        //WHEN
        //THEN
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> useCaseHandler.handler(useCase));
        Assertions.assertEquals(exception.getMessage(), expectedErrorMessage);
    }

}

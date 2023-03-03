package url.usecase;

import com.example.url.usecase.Redirect;
import com.example.url.usecase.RedirectUseCaseHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import url.port.FakeUrlPort;
import com.example.url.port.UrlPort;

class RedirectUseCaseHandlerTest {

    private RedirectUseCaseHandler useCaseHandler;

    @BeforeEach
    void setUp(){
        UrlPort urlPort = new FakeUrlPort();
        useCaseHandler = new RedirectUseCaseHandler(urlPort);
    }

    @Test
    void Should_ReturnLongUrl_WhenValidShortened(){
        //GIVEN
        Redirect redirect = new Redirect("abc");
        //WHEN
        String result = useCaseHandler.handler(redirect);
        //THEN
        Assertions.assertEquals("http://google.com", result);

    }

    @Test
    void Should_ThrowException_WhenInvalidUrl(){
        //GIVEN
        Redirect redirect = new Redirect("abcd");
        String expectedErrorMessage = "Error";
        //WHEN
        //THEN
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> useCaseHandler.handler(redirect));
        Assertions.assertEquals(exception.getMessage(), expectedErrorMessage);

    }
}

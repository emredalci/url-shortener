package url.port;

import com.example.url.model.Url;
import com.example.url.port.UrlPort;

public class FakeUrlPort implements UrlPort {

    @Override
    public void saveUrl(Url url) {
        return;
    }

    @Override
    public String retrieveLongUrl(String shortened) {
        if (shortened.equals("abc")) return "http://google.com";
        else throw new RuntimeException("Error");
    }
}

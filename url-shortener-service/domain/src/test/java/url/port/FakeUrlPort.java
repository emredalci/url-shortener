package url.port;

import url.model.Url;

public class FakeUrlPort implements UrlPort{

    @Override
    public void saveUrl(Url url) {
        return;
    }

    @Override
    public String retrieveLongUrl(String shortened) {
        return null;
    }
}

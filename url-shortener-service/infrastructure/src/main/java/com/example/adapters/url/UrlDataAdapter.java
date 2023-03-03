package com.example.adapters.url;

import com.example.url.model.Url;
import com.example.url.port.UrlPort;
import org.springframework.stereotype.Service;

@Service
public class UrlDataAdapter implements UrlPort {
    @Override
    public void saveUrl(Url url) {

    }

    @Override
    public String retrieveLongUrl(String shortened) {
        return null;
    }
}

package com.example.url.port;

import com.example.url.model.Url;

public interface UrlPort {

    void saveUrl(Url url);

    String retrieveLongUrl(String shortened);
}

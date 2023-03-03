package com.example.adapters.url.rest.jpa;

import com.example.adapters.url.exception.UrlNotFoundException;
import com.example.adapters.url.rest.jpa.entity.UrlEntity;
import com.example.adapters.url.rest.jpa.repository.UrlRepository;
import com.example.url.model.Url;
import com.example.url.port.UrlPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlDataAdapter implements UrlPort {

    private final UrlRepository urlRepository;


    @Override
    public void saveUrl(Url url) {
        UrlEntity urlEntity = UrlEntity.builder()
                .userId(url.userId())
                .url(url.longUrl())
                .shortened(url.shortUrl())
                .build();
        urlRepository.save(urlEntity);
    }

    @Override
    public String retrieveLongUrl(String shortened) {
        return urlRepository.findByShortened(shortened)
                .map(UrlEntity::getShortened)
                .orElseThrow(() -> new UrlNotFoundException("Error"));

    }
}

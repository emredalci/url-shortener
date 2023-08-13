package com.example.adapters.url.rest.jpa;

import com.example.adapters.url.rest.jpa.entity.UrlEntity;
import com.example.adapters.url.rest.jpa.repository.UrlRepository;
import com.example.common.enums.CacheNames;
import com.example.common.exception.BusinessException;
import com.example.url.model.Url;
import com.example.url.port.UrlPort;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
    @Cacheable(cacheNames = CacheNames.URL, unless = "#result == null")
    public String retrieveLongUrl(String shortened) {
        return urlRepository.findByShortened(shortened)
                .map(UrlEntity::getUrl)
                .orElseThrow(() -> new BusinessException("url.not.found.error"));

    }
}

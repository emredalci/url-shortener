package com.example.integration;

import com.example.adapters.url.exception.UrlNotFoundException;
import com.example.adapters.url.rest.jpa.entity.UrlEntity;
import com.example.adapters.url.rest.jpa.repository.UrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UrlRepositoryIntegrationTest extends AbstractTestContainersIT{

    @Autowired
    UrlRepository urlRepository;

    @Test
    void Should_ReturnShortened(){
        //GIVEN
        String shortened = "WN6jeG";
        String expected = "https://www.emlakjet.com/satilik-konut/istanbul-adalar/?fiyat_trendi=dusen&icerik_turu=video&fiyat_analizi=firsat&sanal_tur=var";
        //WHEN
        String actual = urlRepository.findByShortened(shortened)
                .map(UrlEntity::getUrl)
                .orElseThrow(() -> new UrlNotFoundException("url.not.found.error"));
        //THEN
        Assertions.assertTrue(POSTGRES_SQL_CONTAINER.isRunning());
        Assertions.assertEquals(expected, actual);
    }
}

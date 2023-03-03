package com.example.adapters.url.rest.jpa.repository;

import com.example.adapters.url.rest.jpa.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

    Optional<UrlEntity> findByShortened(String shortened);
}

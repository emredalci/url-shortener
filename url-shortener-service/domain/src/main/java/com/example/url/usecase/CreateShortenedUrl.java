package com.example.url.usecase;

import com.example.common.model.UseCase;

public record CreateShortenedUrl(long userId, String url) implements UseCase {

}

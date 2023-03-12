package com.example.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Getter
public enum CacheTTL {

    URL(CacheNames.URL, 1L, TimeUnit.MINUTES);

    private final String cacheName;
    private final Long duration;
    private final TimeUnit timeType;

    private static final List<CacheTTL> CACHE_TTL_LIST;

    static {
        CACHE_TTL_LIST = Arrays.stream(values()).toList();
    }
}

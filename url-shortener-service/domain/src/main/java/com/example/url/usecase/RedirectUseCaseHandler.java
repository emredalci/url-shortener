package com.example.url.usecase;

import com.example.common.usecase.UseCaseHandler;
import com.example.common.DomainComponent;
import lombok.RequiredArgsConstructor;
import com.example.url.port.UrlPort;

@RequiredArgsConstructor
@DomainComponent
public class RedirectUseCaseHandler implements UseCaseHandler<String, Redirect> {

    private final UrlPort urlPort;

    @Override
    public String handler(Redirect useCase) {
        return urlPort.retrieveLongUrl(useCase.shortened());
    }
}

package url.usecase;

import common.DomainComponent;
import common.usecase.UseCaseHandler;
import lombok.RequiredArgsConstructor;
import url.port.UrlPort;

@RequiredArgsConstructor
@DomainComponent
public class RedirectUseCaseHandler implements UseCaseHandler<String, Redirect> {

    private final UrlPort urlPort;

    @Override
    public String handler(Redirect useCase) {
        return urlPort.retrieveLongUrl(useCase.shortened());
    }
}

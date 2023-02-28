package url.usecase;

import common.DomainComponent;
import common.usecase.UseCaseHandler;
import lombok.RequiredArgsConstructor;
import url.model.Url;
import url.port.UrlPort;
import user.port.UserPort;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@DomainComponent
@RequiredArgsConstructor
public class CreateShortenedUrlUseCaseHandler implements UseCaseHandler<String, CreateShortenedUrl> {

    public static final String ALGORITHM = "MD5";

    private final UserPort userPort;
    private final UrlPort urlPort;

    @Override
    public String handler(CreateShortenedUrl useCase) {
        userPort.isExistUser(useCase.userId());
        String shortened = getShortened(useCase);
        urlPort.saveUrl(new Url(useCase.userId(), useCase.url(), shortened));
        return shortened;
    }

    private String getShortened(CreateShortenedUrl useCase) {

        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = messageDigest.digest(useCase.url().getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(bytes).substring(0,6);
    }
}

package com.example.url.usecase;

import com.example.common.exception.BusinessException;
import com.example.common.usecase.UseCaseHandler;
import com.example.user.port.UserPort;
import com.example.url.model.Url;
import com.example.common.DomainComponent;
import lombok.RequiredArgsConstructor;
import com.example.url.port.UrlPort;
import org.apache.commons.validator.routines.UrlValidator;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@DomainComponent
@RequiredArgsConstructor
public class CreateShortenedUrlUseCaseHandler implements UseCaseHandler<String, CreateShortenedUrl> {

    private static final String ALGORITHM = "MD5";
    private static final String REGEX_FOR_REPLACE = "/";

    private final UserPort userPort;
    private final UrlPort urlPort;

    @Override
    public String handler(CreateShortenedUrl useCase) {
        boolean isValidUrl = UrlValidator.getInstance().isValid(useCase.url());
        if (Boolean.FALSE.equals(isValidUrl)) {
            throw new BusinessException("url.not.valid.error");
        }
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
            throw new BusinessException("algorithm.invalid.error");
        }
        byte[] bytes = messageDigest.digest(useCase.url().getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(bytes).replace(REGEX_FOR_REPLACE, "").substring(0,6);
    }
}

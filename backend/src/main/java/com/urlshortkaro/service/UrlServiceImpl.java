package com.urlshortkaro.service;

import com.urlshortkaro.config.UrlProperties;
import com.urlshortkaro.dto.CreateShortUrlRequest;
import com.urlshortkaro.dto.CreateShortUrlResponse;
import com.urlshortkaro.entity.Url;
import com.urlshortkaro.exception.*;
import com.urlshortkaro.mapper.UrlMapper;
import com.urlshortkaro.repository.UrlRepository;
import com.urlshortkaro.utils.ShortCodeGenerator;
import com.urlshortkaro.validator.AliasValidator;
import com.urlshortkaro.validator.UrlValidator;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Clock;
import java.time.LocalDateTime;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    private final UrlValidator urlValidator;
    private final ShortCodeGenerator shortCodeGenerator;
    private final UrlProperties urlProperties;
    private final UrlMapper urlMapper;
    private final Clock clock;
    private final AliasValidator aliasValidator;

    public UrlServiceImpl(UrlRepository urlRepository,
                          UrlValidator urlValidator,
                          ShortCodeGenerator shortCodeGenerator,
                          UrlProperties urlProperties, UrlMapper urlMapper,
                          AliasValidator aliasValidator, Clock clock, AliasValidator aliasValidator1) {
        this.urlRepository = urlRepository;
        this.urlValidator = urlValidator;
        this.shortCodeGenerator = shortCodeGenerator;
        this.urlProperties = urlProperties;
        this.urlMapper = urlMapper;
        this.clock = clock;
        this.aliasValidator = aliasValidator1;
    }

    @Override
    public CreateShortUrlResponse create(CreateShortUrlRequest request) {

        urlValidator.validate(request.getUrl());

        LocalDateTime now = LocalDateTime.now(clock);

        LocalDateTime expiryDate;

        if (request.getExpiresAt() == null) {
            expiryDate = now.plusDays(
                    urlProperties.getDefaultExpiryDays()
            );
        } else {
            expiryDate = request.getExpiresAt();
        }

        if (expiryDate.isBefore(now)) {
            throw new InvalidUrlException(ErrorCode.URL_EXPIRED);
        }

        String shortCode;

        if (request.getAlias() != null &&
                !request.getAlias().isBlank()) {

            aliasValidator.validate(request.getAlias());

            if (urlRepository.existsByShortCode(request.getAlias())) {
                throw new AliasAlreadyExistsException(
                        ErrorCode.ALIAS_ALREADY_EXISTS
                );
            }

            shortCode = request.getAlias();

        } else {
            shortCode = generateUniqueShortCode();
        }

        Url url = Url.builder()
                .shortCode(shortCode)
                .originalUrl(request.getUrl())
                .expiresAt(expiryDate)
                .active(true)
                .createdAt(now)
                .updatedAt(now)
                .build();

        Url savedUrl = urlRepository.save(url);

        return urlMapper.toCreateShortUrlResponse(savedUrl);
    }

    @Override
    public URI getRedirectUrl(String shortCode) {

        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() ->
                        new UrlNotFoundException(ErrorCode.URL_NOT_FOUND));

        if (!url.isActive()) {
            throw new UrlNotFoundException(ErrorCode.URL_NOT_FOUND);
        }

        if (url.getExpiresAt() != null &&
                url.getExpiresAt().isBefore(LocalDateTime.now(clock))) {
            throw new InvalidUrlException(ErrorCode.URL_EXPIRED);
        }

        return URI.create(url.getOriginalUrl());
    }

    private String generateUniqueShortCode(){
        String shortCode;

        for (int retry = 0; retry < urlProperties.getMaxRetryCount(); retry++) {
            shortCode = shortCodeGenerator.generate();
            if (!urlRepository.existsByShortCode(shortCode)) {
                return shortCode;
            }
        }
        throw new ShortCodeGenerationException(
                ErrorCode.SHORT_CODE_GENERATION_FAILED
        );
    }
}
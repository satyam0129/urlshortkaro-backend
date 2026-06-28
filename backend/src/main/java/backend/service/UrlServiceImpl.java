package backend.service;

import backend.config.UrlProperties;
import backend.dto.CreateShortUrlRequest;
import backend.dto.CreateShortUrlResponse;
import backend.entity.Url;
import backend.exception.ErrorCode;
import backend.exception.InvalidUrlException;
import backend.exception.ShortCodeGenerationException;
import backend.mapper.UrlMapper;
import backend.repository.UrlRepository;
import backend.service.UrlService;
import backend.utils.ShortCodeGenerator;
import backend.validator.UrlValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    private final UrlValidator urlValidator;
    private final ShortCodeGenerator shortCodeGenerator;
    private final UrlProperties urlProperties;
    private final UrlMapper urlMapper;

    public UrlServiceImpl(UrlRepository urlRepository,
                          UrlValidator urlValidator,
                          ShortCodeGenerator shortCodeGenerator,
                          UrlProperties urlProperties, UrlMapper urlMapper) {
        this.urlRepository = urlRepository;
        this.urlValidator = urlValidator;
        this.shortCodeGenerator = shortCodeGenerator;
        this.urlProperties = urlProperties;
        this.urlMapper = urlMapper;
    }

    @Override
    public CreateShortUrlResponse create(CreateShortUrlRequest request) {

        urlValidator.validate(request.getUrl());

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime expiryDate = request.getExpiresAt();

        if (expiryDate == null) {
            expiryDate = now.plusDays(urlProperties.getDefaultExpiryDays());
        }

        if (expiryDate.isBefore(now)) {
            throw new InvalidUrlException(ErrorCode.URL_EXPIRED);
        }

        String shortCode = null;

        for (int retry = 0; retry < urlProperties.getMaxRetryCount(); retry++) {

            shortCode = shortCodeGenerator.generate();

            if (!urlRepository.existsByShortCode(shortCode)) {
                break;
            }
        }

        if (shortCode == null || urlRepository.existsByShortCode(shortCode)) {
            throw new ShortCodeGenerationException(ErrorCode.SHORT_CODE_GENERATION_FAILED);
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
}
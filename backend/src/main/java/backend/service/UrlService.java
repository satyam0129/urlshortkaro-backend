package backend.service;

import backend.dto.CreateShortUrlRequest;
import backend.dto.CreateShortUrlResponse;
import jakarta.validation.Valid;

public interface UrlService {

    CreateShortUrlResponse create(@Valid CreateShortUrlRequest request);
}

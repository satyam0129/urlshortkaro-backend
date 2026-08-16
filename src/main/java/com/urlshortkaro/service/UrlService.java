package com.urlshortkaro.service;

import com.urlshortkaro.dto.CreateShortUrlRequest;
import com.urlshortkaro.dto.CreateShortUrlResponse;
import jakarta.validation.Valid;

import java.net.URI;

public interface UrlService {

    CreateShortUrlResponse create(@Valid CreateShortUrlRequest request);

    URI getRedirectUrl(String shortCode);

    void deactivate(Long id);

    CreateShortUrlResponse reactivate(Long id);
}

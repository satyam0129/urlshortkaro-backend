package com.urlshortkaro.mapper;

import com.urlshortkaro.config.UrlProperties;
import com.urlshortkaro.dto.CreateShortUrlResponse;
import com.urlshortkaro.entity.Url;
import org.springframework.stereotype.Component;

@Component
public class UrlMapper {

    private final UrlProperties urlProperties;

    public UrlMapper(UrlProperties urlProperties) {
        this.urlProperties = urlProperties;
    }

    public CreateShortUrlResponse toCreateShortUrlResponse(Url url) {

        CreateShortUrlResponse response = new CreateShortUrlResponse();

        response.setId(url.getId());
        response.setShortCode(url.getShortCode());
        response.setShortUrl(urlProperties.getBaseUrl() + "/urls/" + url.getShortCode());
        response.setOriginalUrl(url.getOriginalUrl());
        response.setExpiresAt(url.getExpiresAt());
        response.setCreatedAt(url.getCreatedAt());

        return response;
    }
}
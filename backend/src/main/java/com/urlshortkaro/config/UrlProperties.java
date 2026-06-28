package com.urlshortkaro.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.url")
public class UrlProperties {

    private int defaultExpiryDays;
    private int shortCodeLength;
    private String shortCodeCharset;
    private String baseUrl;
    private int maxRetryCount;

    public int getDefaultExpiryDays() {
        return defaultExpiryDays;
    }

    public void setDefaultExpiryDays(int defaultExpiryDays) {
        this.defaultExpiryDays = defaultExpiryDays;
    }

    public int getShortCodeLength() {
        return shortCodeLength;
    }

    public void setShortCodeLength(int shortCodeLength) {
        this.shortCodeLength = shortCodeLength;
    }

    public String getShortCodeCharset() {
        return shortCodeCharset;
    }

    public void setShortCodeCharset(String shortCodeCharset) {
        this.shortCodeCharset = shortCodeCharset;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public int getMaxRetryCount() {
        return maxRetryCount;
    }

    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

}
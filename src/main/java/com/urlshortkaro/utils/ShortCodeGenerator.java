package com.urlshortkaro.utils;

import com.urlshortkaro.config.UrlProperties;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class ShortCodeGenerator {

    private final UrlProperties urlProperties;
    private final SecureRandom secureRandom;

    public ShortCodeGenerator(UrlProperties urlProperties) {
        this.urlProperties = urlProperties;
        this.secureRandom = new SecureRandom();
    }

    public String generate() {

        StringBuilder shortCode = new StringBuilder();

        String charset = urlProperties.getShortCodeCharset();

        for (int index = 0; index < urlProperties.getShortCodeLength(); index++) {

            int randomIndex = secureRandom.nextInt(charset.length());

            shortCode.append(charset.charAt(randomIndex));
        }

        return shortCode.toString();
    }
}
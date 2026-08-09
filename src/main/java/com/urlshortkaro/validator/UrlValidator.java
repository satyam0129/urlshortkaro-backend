package com.urlshortkaro.validator;

import com.urlshortkaro.exception.ErrorCode;
import com.urlshortkaro.exception.InvalidUrlException;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class UrlValidator {

    public void validate(String url) {

        if (url == null || url.isBlank()) {
            throwInvalidUrlException();
        }

        try {
            URI uri = new URI(url);
            validateScheme(uri);
            validateHost(uri);
            validatePort(uri);
        } catch (URISyntaxException | IllegalArgumentException ex) {
            throwInvalidUrlException();
        }
    }

    private void validateScheme(URI uri) {

        String scheme = uri.getScheme();

        if (scheme == null || (!scheme.equalsIgnoreCase("http") && !scheme.equalsIgnoreCase("https"))) {
            throwInvalidUrlException();
        }
    }

    private void validateHost(URI uri) {

        String host = uri.getHost();

        if (host == null || host.isBlank()) {
            throwInvalidUrlException();
        }
    }

    private void validatePort(URI uri) {

        if (uri.getPort() == 0 || uri.getPort() < -1 || uri.getPort() > 65535) {
            throwInvalidUrlException();
        }
    }

    private void throwInvalidUrlException() {
        throw new InvalidUrlException(ErrorCode.INVALID_URL);
    }
}
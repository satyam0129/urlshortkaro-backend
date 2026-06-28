package backend.validator;

import backend.exception.ErrorCode;
import backend.exception.InvalidUrlException;
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

            if (uri.getScheme() == null ||
                    (!uri.getScheme().equalsIgnoreCase("http")
                            && !uri.getScheme().equalsIgnoreCase("https"))) {
                throwInvalidUrlException();
            }

            if (uri.getHost() == null || uri.getHost().isBlank()) {
                throwInvalidUrlException();
            }

        } catch (URISyntaxException ex) {
            throwInvalidUrlException();
        }
    }

    private void throwInvalidUrlException() {
        throw new InvalidUrlException(ErrorCode.INVALID_URL);
    }
}
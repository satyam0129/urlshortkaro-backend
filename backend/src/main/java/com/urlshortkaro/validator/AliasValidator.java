package com.urlshortkaro.validator;

import com.urlshortkaro.exception.ErrorCode;
import com.urlshortkaro.exception.InvalidAliasException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class AliasValidator {

    private static final Pattern ALIAS_PATTERN =
            Pattern.compile("^[a-zA-Z0-9_-]{3,20}$");

    public void validate(String alias) {

        if (alias == null || alias.isBlank()) {
            throw new InvalidAliasException(ErrorCode.INVALID_ALIAS);
        }

        if (!ALIAS_PATTERN.matcher(alias).matches()) {
            throw new InvalidAliasException(ErrorCode.INVALID_ALIAS);
        }
    }
}
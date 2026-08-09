package com.urlshortkaro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateShortUrlRequest {

    @NotBlank
    private String url;

    private LocalDateTime expiresAt;

    private String alias;
}

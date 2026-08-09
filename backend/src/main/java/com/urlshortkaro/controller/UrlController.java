package com.urlshortkaro.controller;

import com.urlshortkaro.dto.CreateShortUrlRequest;
import com.urlshortkaro.dto.CreateShortUrlResponse;
import com.urlshortkaro.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }


    @PostMapping("/create")
    public ResponseEntity<CreateShortUrlResponse> createShortUrl(
            @RequestBody CreateShortUrlRequest request) {

        CreateShortUrlResponse response = urlService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode) {

        URI originalUrl = urlService.getRedirectUrl(shortCode);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(originalUrl)
                .build();
    }

}
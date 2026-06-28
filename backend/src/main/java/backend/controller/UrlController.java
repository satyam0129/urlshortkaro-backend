package backend.controller;

import backend.dto.CreateShortUrlRequest;
import backend.dto.CreateShortUrlResponse;
import backend.dto.UrlResponse;
import backend.entity.Url;
import backend.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${app.api.base-path}/urls")
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


}
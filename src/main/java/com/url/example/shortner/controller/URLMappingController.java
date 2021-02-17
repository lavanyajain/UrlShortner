package com.url.example.shortner.controller;

import com.url.example.shortner.modal.CreateShortURLResponse;
import com.url.example.shortner.modal.CreateShortUrlRequest;
import com.url.example.shortner.services.FetchAccessHistoryService;
import com.url.example.shortner.services.FetchUrlEntriesService;
import com.url.example.shortner.services.RedirectLongUrlService;
import com.url.example.shortner.services.UrlShortenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;

@Controller
@RestController
@RequestMapping("/api")
public class URLMappingController {
    @Autowired
    private FetchUrlEntriesService fetchUrlEntriesService;

    @Autowired
    private FetchAccessHistoryService accessHistoryService;

    @Autowired
    private UrlShortenService urlShortenService;

    @Autowired
    RedirectLongUrlService redirectLongUrlService;

    @PostMapping(value = "/create", consumes = "application/json" ,produces = "application/json")
    public CreateShortURLResponse createShortURL(@RequestBody CreateShortUrlRequest createShortUrlRequest) {
        return urlShortenService.createShortUrl(createShortUrlRequest.getUrl());
    }

    @GetMapping(value = "/{url}")
    public void navigateToLongUrl(HttpServletResponse httpServletResponse, @PathVariable String url) throws Exception {
        httpServletResponse.sendRedirect(redirectLongUrlService.getLongUrl(url));
    }

    @GetMapping(value = "/entries")
    public HashMap<String, String> getAllUrlMapEntries() throws SQLException {
        return fetchUrlEntriesService.getAllUrlMapEntries();
    }

    @GetMapping(value = "/history")
    public HashMap<String, Integer> getAccessHistory() throws Exception {
        return accessHistoryService.getAccessHistory();
    }
}

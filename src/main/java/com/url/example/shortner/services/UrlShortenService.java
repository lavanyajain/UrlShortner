package com.url.example.shortner.services;

import com.url.example.shortner.api.UrlShortnerApi;
import com.url.example.shortner.modal.CreateShortURLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenService {
    @Autowired
    private UrlShortnerApi shortnerApi;

    public CreateShortURLResponse createShortUrl(String url) {
        CreateShortURLResponse response = shortnerApi.configureUrlEntry(url);
        return response;
    }
}

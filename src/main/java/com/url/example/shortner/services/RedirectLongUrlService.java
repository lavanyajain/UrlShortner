package com.url.example.shortner.services;

import com.url.example.shortner.api.FetchLongUrlApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedirectLongUrlService {
    @Autowired
    private FetchLongUrlApi longUrlApi;

    public String getLongUrl(String shortUrl) throws Exception {
        return longUrlApi.getLongUrl(shortUrl);
    }
}

package com.url.example.shortner.services;

import com.url.example.shortner.api.FetchLongUrlApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;

@Service
public class FetchUrlEntriesService {

    @Autowired
    private FetchLongUrlApi fetchLongUrlApi;

    public HashMap<String,String> getAllUrlMapEntries() throws SQLException {
        return fetchLongUrlApi.getAllMapEntries();
    }
}

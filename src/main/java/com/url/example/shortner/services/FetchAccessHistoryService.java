package com.url.example.shortner.services;

import com.url.example.shortner.api.AccessHistoryApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;

@Service
public class FetchAccessHistoryService {
    @Autowired
    private AccessHistoryApi accessHistoryApi;

    public HashMap<String, Integer> getAccessHistory() throws SQLException {
        return accessHistoryApi.getAccessHistory();
    }

}

package com.url.example.shortner.api;

import com.url.example.shortner.lib.QueryExecutor;
import com.url.example.shortner.modal.QueryExecutorResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AccessHistoryApi {
    @Value("${court.booking.database.driver}")
    private String JDBC_DRIVER;

    @Value("${court.booking.database.url}")
    private String DB_URL;

    @Value("${court.booking.database.username}")
    private String USER_NAME;

    @Value("${court.booking.database.password}")
    private String PASSWORD;

    private final QueryExecutor queryExecutor = new QueryExecutor();

    public void updateAccessHistory(String url, int count) throws Exception {
        String query = "UPDATE urlmap SET count=" + count + " WHERE decoded='" + url + "';";
        queryExecutor.executeUpdate(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, query);
    }

    public HashMap<String, Integer> getAccessHistory() throws SQLException {
        String query = "select * from urlmap";
        HashMap<String, Integer> entries = new HashMap<>();
        QueryExecutorResponse queryExecutorResponse = queryExecutor.executeQuery(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, query);
        ResultSet resultSet = queryExecutorResponse.getResultSet();
        while (resultSet.next()) {
            entries.put(resultSet.getString("decoded"), resultSet.getInt("count"));
        }
        return entries.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }
}

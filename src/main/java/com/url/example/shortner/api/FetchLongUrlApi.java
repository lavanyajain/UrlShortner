package com.url.example.shortner.api;

import com.url.example.shortner.exception.UrlNotFoundException;
import com.url.example.shortner.lib.QueryExecutor;
import com.url.example.shortner.lib.StringConverter;
import com.url.example.shortner.modal.QueryExecutorResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Component
public class FetchLongUrlApi {
    @Value("${court.booking.database.driver}")
    private String JDBC_DRIVER;

    @Value("${court.booking.database.url}")
    private String DB_URL;

    @Value("${court.booking.database.username}")
    private String USER_NAME;

    @Value("${court.booking.database.password}")
    private String PASSWORD;

    @Autowired
    AccessHistoryApi accessHistoryApi;

    private final QueryExecutor queryExecutor = new QueryExecutor();

    private static final Logger logger = LoggerFactory.getLogger(UrlShortnerApi.class);
    public String getLongUrl(String url) throws Exception {
        String encodedUrl = StringConverter.encodeString(url);
        String longUrl = "";
        String query = "select * from urlmap where encoded='" + url + "';";
        QueryExecutorResponse queryExecutorResponse = queryExecutor.executeQuery(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, query);
        ResultSet resultSet = queryExecutorResponse.getResultSet();
        while (resultSet.next()) {
            longUrl = resultSet.getString("decoded");
            int count = resultSet.getInt("count");
            accessHistoryApi.updateAccessHistory(longUrl, count+1);
        }
        if(longUrl.length() == 0)
            throw new UrlNotFoundException("This URL entry not found");
        return longUrl;
    }

    public HashMap<String, String> getAllMapEntries() throws SQLException {
        String query = "select * from urlmap";
        HashMap<String, String> entries = new HashMap<>();
        QueryExecutorResponse queryExecutorResponse = queryExecutor.executeQuery(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, query);
        ResultSet resultSet = queryExecutorResponse.getResultSet();
        while (resultSet.next()) {
            entries.put(resultSet.getString("decoded"), resultSet.getString("encoded"));
        }
        return entries;
    }



}

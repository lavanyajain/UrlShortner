package com.url.example.shortner.api;

import com.url.example.shortner.lib.QueryExecutor;
import com.url.example.shortner.modal.CreateShortURLResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;

@Component
public class UrlShortnerApi {

    @Value("${court.booking.database.driver}")
    private String JDBC_DRIVER;

    @Value("${court.booking.database.url}")
    private String DB_URL;

    @Value("${court.booking.database.username}")
    private String USER_NAME;

    @Value("${court.booking.database.password}")
    private String PASSWORD;

    private final QueryExecutor queryExecutor = new QueryExecutor();

    private static final Logger logger = LoggerFactory.getLogger(UrlShortnerApi.class);

    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    public CreateShortURLResponse configureUrlEntry(String url) {
        if(!isValidUrl(url))
            return new CreateShortURLResponse(FAILURE, null, "URL is invalid please enter valid URL and try again to shorten it");
        String encodedUrl = String.valueOf(url.hashCode()).replaceAll("-", "");
        String query = "insert into UrlMap(encoded, decoded, count) values ('" + encodedUrl + "','" + url  + "',0);";
        try {
            queryExecutor.executeUpdate(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, query);
        } catch (SQLException exception) {
            return new CreateShortURLResponse(FAILURE, null, exception.getMessage());
        } catch (Exception exception) {
            return new CreateShortURLResponse(FAILURE, null, exception.getMessage());
        }
        return new CreateShortURLResponse(SUCCESS, encodedUrl, null);
    }

    private boolean isValidUrl(String url) {
        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
        return true;
    }
}

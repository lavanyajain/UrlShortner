package com.url.example.shortner.lib;

import com.url.example.shortner.modal.QueryExecutorResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;

public class QueryExecutor {

    private static final Logger logger = LoggerFactory.getLogger(QueryExecutor.class);

    public QueryExecutorResponse executeQuery(String jdbc_driver, String db_url, String username, String password, String query) {
        QueryExecutorResponse queryExecutorResponse = new QueryExecutorResponse();
        try {
            Class.forName(jdbc_driver);
            queryExecutorResponse.setConnection(DriverManager.getConnection(db_url, username, password));
            queryExecutorResponse.setStatement((queryExecutorResponse.getConnection().createStatement()));
            queryExecutorResponse.setResultSet(queryExecutorResponse.getStatement().executeQuery(query));
        }
        catch (Exception exception) {
            logger.error("Error while executing {} SQL query with the error message {}", query, exception.getMessage());
        }
        return queryExecutorResponse;
    }

    public void executeUpdate(String jdbc_driver, String db_url, String username, String password, String query) throws Exception {
        QueryExecutorResponse queryExecutorResponse = new QueryExecutorResponse();
        try {
            Class.forName(jdbc_driver);
            queryExecutorResponse.setConnection(DriverManager.getConnection(db_url, username, password));
            queryExecutorResponse.setStatement((queryExecutorResponse.getConnection().createStatement()));
            queryExecutorResponse.getStatement().executeUpdate(query);
        }
        catch (Exception exception) {
            logger.error("Error while executing {} SQL query with the error message {}", query, exception.getMessage());
            throw new Exception(exception.getMessage());
        }
        finally {
            queryExecutorResponse.getStatement().close();
            queryExecutorResponse.getConnection().close();
        }
    }
}

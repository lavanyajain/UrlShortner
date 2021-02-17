package com.url.example.shortner.modal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryExecutorResponse {
    private Statement statement;
    private Connection connection;
    private ResultSet resultSet;

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
}

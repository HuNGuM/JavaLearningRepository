package com.example.demo.liquibase_clone_new.main.java.org.example.migration;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DataSource dataSource;

    static {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("your_value");
        ds.setUsername("your_value");
        ds.setPassword("your_value");
        ds.setInitialSize(5);
        ds.setMaxTotal(10);
        dataSource = ds;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
package com.nestedvariables.dev.Discord.Quiver;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class SQLDriver {
    private static HikariDataSource ds;
    
    static{
        Credentials credentials = new Credentials();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(credentials.databaseURL);
        config.setUsername(credentials.databaseUser);
        config.setPassword(credentials.databasePassword);  
        //config.setDriverClassName("com.mysql.jdbc.Driver");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    public static Connection getConn() throws SQLException {
        return ds.getConnection();
    }
}
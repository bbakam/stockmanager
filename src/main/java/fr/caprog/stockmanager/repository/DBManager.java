package fr.caprog.stockmanager.repository;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {

    private static final Logger logger = Logger.getLogger(DBManager.class.getName());;

    private String url;
    private String username;
    private String password;

    public DBManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        logger.info("Create DB connection");
        return DriverManager.getConnection(this.url,this.username,this.password);
    }
}

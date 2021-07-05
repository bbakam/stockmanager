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

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        logger.info("Create DB connection");
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/prod","root","rootroot");
    }

    public void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
                logger.info("Closing DB connection");
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error while closing connection",e);
            }
        }
    }

    public void closePreparedStatement(PreparedStatement ps) {
        if(ps!=null){
            try {
                ps.close();
                logger.info("Closing Prepared Statement");
            } catch (SQLException e) {
                logger.log(Level.SEVERE,"Error while closing prepared statement",e);
            }
        }
    }

    public void closeResultSet(ResultSet rs) {
        if(rs!=null){
            try {
                rs.close();
                logger.info("Closing Result Set");
            } catch (SQLException e) {
                logger.log(Level.SEVERE,"Error while closing result set",e);
            }
        }
    }
}

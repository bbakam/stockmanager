package fr.caprog.stockmanager.repository.user;

import fr.caprog.stockmanager.domain.User;
import fr.caprog.stockmanager.repository.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserRepositoryImpl implements UserRepository {

    private static final String FIND_ONE_USER = "select * from user where id = ?";
    private static final String SAVE_USER = "insert into user(name, password, profile) values (?, ?, ?)";
    private static final String DELETE_USER = "delete from user where id = ?";
    private static final int DELETED_ONE_DATA = 1;
    private static final int NOT_DELETED_DATA = 0;
    private static final String UPDATE_PROFILE = "update user set profile = ? where id = ?";
    private static final String UPDATE_PASSWORD = "update password from user = ? where id = ?";
    private static final String GET_USERS = "select * from user";

    private DBManager dbManager;

    public UserRepositoryImpl(DBManager dbManager) { this.dbManager = dbManager; }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findOne(int id) throws SQLException {
        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_ONE_USER)) {

            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setProfile(rs.getString("profile"));
                    return user;
                }
                return null;
            }
        }
    }

    @Override
    public void save(String username, String password, String profile) throws SQLException {
        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(SAVE_USER)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, profile);
            ps.executeUpdate();

        }
    }

    @Override
    public void delete(int id) throws Exception {
        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_USER)) {

            ps.setInt(1, id);

            if(ps.executeUpdate() > DELETED_ONE_DATA)
                throw new Exception("Multiple lines was deleted!");

            if(ps.executeUpdate() == NOT_DELETED_DATA)
                throw new Exception("Multiple lines was deleted!");
        }
    }

    @Override
    public void updateProfile(int id, String profile) throws SQLException {
        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_PROFILE)) {

            ps.setInt(2, id);
            ps.setString(1, profile);
            ps.executeUpdate();
        }
    }

    @Override
    public void updatePassword(int id, String password) throws SQLException {
        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_PASSWORD)) {

            ps.setInt(2, id);
            ps.setString(1, password);
            ps.executeUpdate();
        }
    }

    @Override
    public List<User> findAll() throws SQLException {

        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_USERS);
             ResultSet rs = ps.executeQuery()) {

            List<User> users = new ArrayList<>();
            while(rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }

            return users;
        }
    }
}

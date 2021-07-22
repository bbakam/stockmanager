package fr.caprog.stockmanager.repository.user;

import fr.caprog.stockmanager.domain.Product;
import fr.caprog.stockmanager.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    User findByUsername(String username);
    User findOne(int id) throws SQLException;
    void save(String username, String password, String profile) throws SQLException;
    void delete(int id) throws Exception;
    void updateProfile(int id, String profile) throws SQLException;
    void updatePassword(int id, String password) throws SQLException;
    List<User> findAll() throws SQLException;
}

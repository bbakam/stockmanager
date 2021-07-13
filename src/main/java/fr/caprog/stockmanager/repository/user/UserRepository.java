package fr.caprog.stockmanager.repository.user;

import fr.caprog.stockmanager.domain.User;

import java.util.List;

public interface UserRepository {
    User findByUsername(String username);
    User findOne(int id);
    void save(String username, String password, String profile);
    void delete(int id);
    void updateProfile(int id, String profile);
    void updatePassword(int id, String password);
    List<User> findAll();
}

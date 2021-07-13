package fr.caprog.stockmanager.repository.user;

import fr.caprog.stockmanager.domain.User;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findOne(int id) {
        return null;
    }

    @Override
    public void save(String username, String password, String profile) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void updateProfile(int id, String profile) {

    }

    @Override
    public void updatePassword(int id, String password) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }
}

package fr.caprog.stockmanager.repository;

import fr.caprog.stockmanager.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {
    List<Product> findAll() throws SQLException, ClassNotFoundException;

    List<Product> findLikeName(String name);

    List<Product> findOne(int id);

    void delete(int id);

    Product updateDescription(int id, String description);

    Product updateStock(int id, int stock);

    Product save(String name, String description, int stock, double price);

    boolean exist(String name);
}

package fr.caprog.stockmanager.repository.product;

import fr.caprog.stockmanager.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {
    List<Product> findAll() throws SQLException, ClassNotFoundException;

    Product findOne(int id) throws SQLException;

    void delete(int id) throws Exception;

    void updateDescription(int id, String description);

    void updateStock(int id, int stock) throws SQLException;

    void save(String name, String description, int stock, double price) throws SQLException;

    int count();
}

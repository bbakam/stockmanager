package fr.caprog.stockmanager.repository;

import fr.caprog.stockmanager.domain.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
    List<Product> findOne(int id);
    List<Product> findLikeName(String name);

    Product save(String name, String description, int stock);

    Product updateDescription(int id, String description);
    Product updateStock(int id, int stock);

    void delete(int id);

    boolean exist(String name);
}

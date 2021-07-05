package fr.caprog.stockmanager.repository;

import fr.caprog.stockmanager.domain.Product;

import java.util.List;

public class ProductRepositoryImpl implements  ProductRepository{

    private DBManager dbManager;

    public ProductRepositoryImpl(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public List<Product> findLikeName(String name) {
        return null;
    }

    @Override
    public List<Product> findOne(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Product updateDescription(int id, String description) {
        return null;
    }

    @Override
    public Product updateStock(int id, int stock) {
        return null;
    }

    @Override
    public Product save(String name, String description, int stock) {
        return null;
    }

    @Override
    public boolean exist(String name) {
        return false;
    }
}

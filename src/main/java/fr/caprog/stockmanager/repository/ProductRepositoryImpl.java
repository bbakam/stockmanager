package fr.caprog.stockmanager.repository;

import fr.caprog.stockmanager.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProductRepositoryImpl implements  ProductRepository{

    private static final Logger logger = Logger.getLogger(DBManager.class.getName());

    private static final String GET_PRODUCTS = "select * from product";

    private DBManager dbManager;

    public ProductRepositoryImpl(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public List<Product> findAll() throws SQLException {

        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_PRODUCTS);
             ResultSet rs = ps.executeQuery()) {

            List<Product> products = new ArrayList<>();
            while(rs.next()) {
                Product product = new Product();
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setPrice(rs.getDouble("price"));
                products.add(product);
            }

            return products;
        }

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
    public Product save(String name, String description, int stock, double price) {
        return null;
    }

    @Override
    public boolean exist(String name) {
        return false;
    }
}

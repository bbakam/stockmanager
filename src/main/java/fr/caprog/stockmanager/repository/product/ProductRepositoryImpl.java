package fr.caprog.stockmanager.repository.product;

import fr.caprog.stockmanager.domain.Product;
import fr.caprog.stockmanager.repository.DBManager;

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
    private static final String SAVE_PRODUCT = "insert into product(name, description, price, stock) values (?, ?, ?, ?)";
    private static final String DELETE_PRODUCT = "delete from product where id = ?" ;
    private static final String UPDATE_DESCRIPTION_PRODUCT = "update product set description = ? where id = ?";
    private static final String FIND_ONE_PRODUCT = "select * from product where id = ?";
    private static final String UPDATE_NAME_PRODUCT = "update product set stock = ? where id = ?";
    private static final int DELETED_ONE_DATA = 1;
    private static final int NOT_DELETED_DATA = 0;
    private static final String COUNT_PRODUCT = "select count (*) from product";

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
    public Product findOne(int id) throws SQLException {
        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_ONE_PRODUCT)) {

            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Product product = new Product();
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setStock(rs.getInt("stock"));
                    product.setPrice(rs.getDouble("price"));
                    return product;
                }
                return null;
            }
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_PRODUCT)) {

            ps.setInt(1, id);

            if(ps.executeUpdate() > DELETED_ONE_DATA)
                throw new Exception("Multiple lines was deleted!");
            
            if(ps.executeUpdate() == NOT_DELETED_DATA)
                throw new Exception("Multiple lines was deleted!");
        }
    }

    @Override
    public void updateDescription(int id, String description) throws SQLException {
        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_DESCRIPTION_PRODUCT)) {

            ps.setInt(2, id);
            ps.setString(1, description);
            ps.executeUpdate();
        }
    }

    @Override
    public void updateStock(int id, int stock) throws SQLException {
        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_NAME_PRODUCT)) {

            ps.setInt(2, id);
            ps.setInt(1, stock);
            ps.executeUpdate();
        }
    }

    @Override
    public void save(String name, String description, int stock, double price) throws SQLException {

        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(SAVE_PRODUCT)) {

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDouble(3, price);
            ps.setInt(4, stock);
            ps.executeUpdate();
        }
    }

    @Override          // SELECT COUNT(*) FROM product;
    public int count() throws SQLException {

        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(COUNT_PRODUCT);
             ResultSet rs = ps.executeQuery()) {

            rs.getInt(1); {
                Product count = new Product();
                count.setName(rs.getString("name"));
            }

            return count();
        }
    }
}
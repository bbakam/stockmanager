import fr.caprog.stockmanager.domain.Product;
import fr.caprog.stockmanager.repository.DBManager;
import fr.caprog.stockmanager.repository.product.ProductRepository;
import fr.caprog.stockmanager.repository.product.ProductRepositoryImpl;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestProductRepositoryIT {

    private static ProductRepository productRepository;
    private static DBManager dbManager;

    @BeforeAll
    public static void setUp() throws ClassNotFoundException, IOException, SQLException, URISyntaxException {
        String dbFileName = "product.sql";
        String scriptDB = getScriptData(dbFileName);

        dbManager = new H2DBManager();
        createDB(dbManager, scriptDB);

        productRepository = new ProductRepositoryImpl(dbManager);
    }

    private static String getScriptData(String dbFileName) throws IOException, URISyntaxException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String absoluteFilePath = Objects.requireNonNull(classLoader.getResource(dbFileName)).toURI().getPath();
        absoluteFilePath = absoluteFilePath.replace("%20", " ");
        File file = new File(absoluteFilePath);

        Scanner reader = new Scanner(file);
        StringBuilder contentBuilder = new StringBuilder();
        while (reader.hasNextLine())
            contentBuilder.append(reader.nextLine());

        return contentBuilder.toString();
    }
    private static void createDB(DBManager dbManager, String scriptDB) throws SQLException {
        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(scriptDB)) {
            ps.execute();
        }
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        try(Connection con = dbManager.getConnection();
            PreparedStatement ps = con.prepareStatement("drop table product;")){
            ps.execute();
        }
    }

    @Test
    @Order(1)
    public void should_save_nintendo_switch_product_in_db() throws SQLException, ClassNotFoundException {

        productRepository.save("nintendo switch", "special edition", 1, 350D);
        List<Product> products = productRepository.findAll();

        assertEquals(1, products.size(), "product count");
        Product product = products.get(0);
        assertEquals("nintendo switch", product.getName(), "product name nintendo switch");
        assertEquals("special edition", product.getDescription(), "product description special edition");
        assertEquals(1, product.getStock(), "product stock 1");
        assertEquals(350D, product.getPrice(), "product price 350");
    }

    @Test
    @Order(2)
    public void should_return_one_product_if_find_all() throws SQLException, ClassNotFoundException {

        List<Product> products = productRepository.findAll();
        assertEquals(1, products.size());
    }

    @Test
    @Order(3)
    public void should_return_nintendo_switch_if_find_one_by_id_1() throws SQLException {

        Product product = productRepository.findOne(1);
        assertNotNull(product, "product returned");
        assertEquals("nintendo switch", product.getName(), "is nintendo switch product");
    }

    @Test
    @Order(4)
    public void should_update_stock_nintendo_switch_product_in_db() throws SQLException {

        productRepository.updateStock(1, 2);
        Product product = productRepository.findOne(1);

        assertEquals(2, product.getStock(), "product stock must be 2");
    }

    @Test
    @Order(5)
    public void should_update_description_nintendo_switch_product_in_db() throws SQLException {

        productRepository.updateDescription(1, "Console Nintendo Switch Neon");
        Product product = productRepository.findOne(1);

        assertEquals("Console Nintendo Switch Neon", product.getDescription(), "product description must be: Console Nintendo Switch Neon");
    }

    @Test
    @Order(6)
    public void should_delete_nintendo_switch_product_in_db() throws Exception {

        productRepository.delete(1);

        Product product = productRepository.findOne(1);

        assertNull(product, "product removed");
    }

    @Test
    @Order(7)
    public void should_count_two_products_if_save_two_products_and_count_products_in_db() throws SQLException {

        productRepository.save("nintendo switch", "special edition", 1, 350D);
        productRepository.save("playstation 5", "classic", 1, 500D);

        int productCount = productRepository.count();

        assertEquals(2, productCount, "product count must be 2");
    }

}

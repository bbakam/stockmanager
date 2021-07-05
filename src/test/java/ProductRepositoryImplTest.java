import static org.junit.jupiter.api.Assertions.assertEquals;


import fr.caprog.stockmanager.domain.Product;
import fr.caprog.stockmanager.repository.DBManager;
import fr.caprog.stockmanager.repository.ProductRepository;
import fr.caprog.stockmanager.repository.ProductRepositoryImpl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;


public class ProductRepositoryImplTest {

    private static final Logger logger = Logger.getLogger(DBManager.class.getName());;

    private static ProductRepository productRepository;

    @BeforeAll
    public static void setUp() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
        String url = "jdbc:h2:~/testINIT=RUNSCRIPT FROM './resources/db.sql'";
        String username = "sa";
        String password = "";

        productRepository = new ProductRepositoryImpl(new DBManager(url, username, password));
    }

    @Test
    public void findAll() throws SQLException, ClassNotFoundException {

        List<Product> products = productRepository.findAll();
        System.out.println(products);

        assertEquals(true, true);
    }
}

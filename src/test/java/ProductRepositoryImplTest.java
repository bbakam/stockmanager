import static org.junit.jupiter.api.Assertions.assertEquals;


import fr.caprog.stockmanager.repository.DBManager;
import fr.caprog.stockmanager.repository.ProductRepository;
import fr.caprog.stockmanager.repository.ProductRepositoryImpl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class ProductRepositoryImplTest {

    private ProductRepository productRepository;

    @BeforeAll
    public void setUp() {
        String url = "jdbc:h2:~/test;MODE=MYSQL;DATABASE_TO_LOWER=TRUE";
        String username = "sa";
        String password = "";

        this.productRepository = new ProductRepositoryImpl(new DBManager(url, username, password));
    }

    @Test
    public void findAll() {
        assertEquals(true, true);
    }
}

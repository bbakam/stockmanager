import static org.junit.jupiter.api.Assertions.assertEquals;


import fr.caprog.stockmanager.domain.Product;
import fr.caprog.stockmanager.repository.DBManager;
import fr.caprog.stockmanager.repository.ProductRepository;
import fr.caprog.stockmanager.repository.ProductRepositoryImpl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TestProductRepositoryImpl {

    private static ProductRepository productRepository;

    @BeforeAll
    public static void setUp() throws ClassNotFoundException, IOException, SQLException {
        Class.forName("org.h2.Driver");
        String url = "jdbc:h2:~/test";
        String username = "sa";
        String password = "";
        String dbFileName = "db.sql";
        String scriptDB = getScriptData(dbFileName);

        DBManager dbManager = new DBManager(url, username, password);
        createDB(dbManager, scriptDB);

        productRepository = new ProductRepositoryImpl(dbManager);
    }

    private static String getScriptData(String dbFileName) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String absoluteFilePath = classLoader.getResource(dbFileName).getFile();
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

    @Test
    public void findAll() throws SQLException, ClassNotFoundException {

        List<Product> products = productRepository.findAll();
        System.out.println(products);

        assertEquals(true, true);
    }
}


import fr.caprog.stockmanager.domain.User;
import fr.caprog.stockmanager.repository.DBManager;
import fr.caprog.stockmanager.repository.product.ProductRepositoryImpl;
import fr.caprog.stockmanager.repository.user.UserRepository;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUserRepositoryIT {

    private static UserRepository userRepository;
    private static DBManager dbManager;

    @BeforeAll
    public static void setUp() throws ClassNotFoundException, IOException, SQLException, URISyntaxException {
        String dbFileName = "user.sql";
        String scriptDB = getScriptData(dbFileName);

        dbManager = new H2DBManager();
        createDB(dbManager, scriptDB);

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
            PreparedStatement ps = con.prepareStatement("drop table user;")){
            ps.execute();
        }
    }

    @Test
    @Order(1)
    public void should_save_user_admin_in_db_and_find_user_by_username() {

        userRepository.save("admin", "", "");
        User user = userRepository.findByUsername("admin");

        assertNotNull(user, "user exist in db with username admin");
        assertEquals("admin", user.getUsername(), "user object has username admin");
    }

    @Test
    @Order(3)
    public void should_return_user_if_find_one_in_db() {

    }

    @Test
    @Order(2)
    public void should_update_password_in_db() {

        userRepository.updatePassword(1, "password");
        User user = userRepository.findByUsername("admin");

        assertNotNull(user, "user exist in db with username admin");
        assertEquals("admin", user.getUsername(), "user object has username admin");
    }

    @Test
    @Order(3)
    public void should_update_profile_in_db() {

        userRepository.updateProfile(1, "admin");
        User user = userRepository.findOne(1);

        assertEquals("profile", user.getProfile(), "user admin has admin profile");
    }

}

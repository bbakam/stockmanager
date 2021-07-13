import fr.caprog.stockmanager.repository.DBManager;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

public class H2DBManager extends DBManager {
    public H2DBManager() throws ClassNotFoundException {
        super("jdbc:h2:~/test", "sa", "", "org.h2.Driver");
    }

    @Override
    public Connection getConnection() throws SQLException {

        return super.getConnection();
    }
}

package apps.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection connection;

    public DatabaseConnection(Credentials cred) {

        try {
            this.connection = DriverManager.getConnection(cred.url, cred.user, cred.password);

            if (this.connection != null) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}


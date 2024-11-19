package apps.database;

import apps.encryption.email.Encoder;

import static configs.ConfigLoader.*;

public class Main {

    private static final Credentials credentials = new Credentials(
            getDataBaseURL(),
            getDataBaseUser(),
            getDataBasePassword());

    public static void main(String[] args) throws Exception {
        System.out.println("HMAC-SHA512 result: " + new Encoder(getEmail(), getDataBaseKey()).encode());

        DatabaseConnection databaseConnection = new DatabaseConnection(credentials);
    }
}

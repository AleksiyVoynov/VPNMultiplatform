package apps.database;

import apps.encryption.email.Encoder;

import static configs.ConfigLoader.*;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("HMAC-SHA512 result: " + new Encoder(getDataBaseEmail(), getDataBaseKey()).encode());

/*        Credentials credentials = new Credentials(
                getDataBaseURL(),
                getDataBaseUser(),
                getDataBasePassword());

        DatabaseConnection databaseConnection = new DatabaseConnection(credentials);

        int a = 0;*/
    }
}

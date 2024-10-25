package apps.multiplatform.temp;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://vpnsuper-qa.c5xik3beuklg.us-east-2.rds.amazonaws.com:5432/qa_vpnsuper";
    private static final String USER = "vpnsuper_qa";
    private static final String PASSWORD = "c3VwZXJ2cG4xMjM=";

    private static final String EMAIL = "oleksii@superunlimited.com";
    private static final String KEY = "yW2zVvgPRTZAgE6ptomrqA==";

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Подключение к базе данных
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            if (connection != null) {
                System.out.println("Успешное подключение к базе данных!");
            } else {
                System.out.println("Не удалось подключиться к базе данных.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        try {
            String hmacResult = hmacSHA512(EMAIL, KEY);
            System.out.println("HMAC-SHA512 result: " + hmacResult);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static String hmacSHA512(String data, String key) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA512");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA512");
        mac.init(secretKeySpec);
        byte[] hmacBytes = mac.doFinal(data.getBytes("UTF-8"));

        StringBuilder hash = new StringBuilder();
        for (byte b : hmacBytes) {
            hash.append(String.format("%02x", b));
        }
        return hash.toString();
    }
}


package configs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    public static String getEncryptionServerListKey() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
            return properties.getProperty("encryptionServerListKey");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load encryption key from config.properties", e);
        }
    }

    public static String getDataBaseKey() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
            return properties.getProperty("dataBaseKey");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load key from config.properties", e);
        }
    }

    public static String getEmail() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
            return properties.getProperty("email");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load email from config.properties", e);
        }
    }

    public static String getDataBaseURL() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
            return properties.getProperty("dataBaseURL");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load url from config.properties", e);
        }
    }

    public static String getDataBaseUser() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
            return properties.getProperty("dataBaseUser");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load user from config.properties", e);
        }
    }

    public static String getDataBasePassword() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
            return properties.getProperty("dataBasePassword");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load password from config.properties", e);
        }
    }
}
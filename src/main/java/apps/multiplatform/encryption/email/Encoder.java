package apps.multiplatform.encryption.email;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encoder {
    private final String data;
    private final String key;


    public Encoder(String data, String key) {
        this.data = data;
        this.key = key;
    }

    public String encode() throws Exception {
        Mac mac = Mac.getInstance("HmacSHA512");
        byte[] bytes = key.getBytes(StandardCharsets.UTF_8);

        SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "HmacSHA512");
        mac.init(secretKeySpec);
        byte[] hmacBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

        StringBuilder hash = new StringBuilder();
        for (byte b : hmacBytes) {
            hash.append(String.format("%02x", b));
        }
        return hash.toString();
    }
}

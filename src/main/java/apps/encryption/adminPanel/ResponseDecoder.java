package apps.encryption.adminPanel;

import io.restassured.response.Response;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.InflaterInputStream;

public class ResponseDecoder {
    private final Response response;
    private final byte[] encryptionKey;

    public ResponseDecoder(Response response, String encryptionKey) {
        this.response = response;
        this.encryptionKey = encryptionKey.getBytes(StandardCharsets.UTF_8);
    }

    // Response Decoding Method
    public String decode() throws Exception {
        // We receive the response body as an array of bytes
        byte[] src = response.getBody().asByteArray();

        // Extraction of IV length
        int ivLen = src[0];
        byte[] iv = new byte[ivLen];
        System.arraycopy(src, 1, iv, 0, ivLen);

        // Extracting encrypted data
        byte[] data = new byte[src.length - ivLen - 1];
        System.arraycopy(src, ivLen + 1, data, 0, data.length);
        int dataLen = data.length;

        // Decryption using AES/CBC/NoPadding
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec secretKey = new SecretKeySpec(encryptionKey, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        byte[] padResult = cipher.doFinal(data);

        // Removing padding
        int padLen = padResult[dataLen - 1];
        byte[] zippedData = new byte[dataLen - padLen];
        System.arraycopy(padResult, 0, zippedData, 0, zippedData.length);

        // Unpacking data using InflaterInputStream
        try (InputStream inflateStream = new InflaterInputStream(new ByteArrayInputStream(zippedData))) {
            byte[] result = inflateStream.readAllBytes();
            return new String(result, StandardCharsets.UTF_8);
        }
    }
}

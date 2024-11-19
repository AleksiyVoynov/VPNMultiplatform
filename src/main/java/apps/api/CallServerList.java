package apps.api;

import apps.encryption.adminPanel.ResponseDecoder;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static configs.ConfigLoader.getEncryptionServerListKey;
import static io.restassured.RestAssured.given;

public class CallServerList {

    public void getServerList() throws Exception {
        RestAssured.baseURI = "https://prod-api.mobilejump.mobi";

        Response response = given()
                .queryParam("platform", "android")
                .queryParam("vip", "true")
                .queryParam("region", "UA")
                .queryParam("app", "com.free.vpn.super.hotspot.open")
                .when()
                .get("/be-data/v3/server_list")
                .then()
                .statusCode(200)
                .extract()
                .response();

        ResponseDecoder decoder = new ResponseDecoder(response, getEncryptionServerListKey());
        String decodedJson = decoder.decode();

        System.out.println("Decoded JSON: " + decodedJson);
    }
}

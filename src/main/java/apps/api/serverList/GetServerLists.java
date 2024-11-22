package apps.api.serverList;

import apps.encryption.adminPanel.ResponseDecoder;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

import static configs.ConfigLoader.getEncryptionServerListKey;
import static io.restassured.RestAssured.given;

public class GetServerLists {
    public List<Server> freeServers;
    public List<Server> vipServers;

    public GetServerLists() throws Exception {
        String response = getServerListRaw();
        this.freeServers = getFreeServers(response);
        this.vipServers = getVipServers(response);
    }

    private String getServerListRaw() throws Exception {
        RestAssured.baseURI = "https://prod-api.mobilejump.mobi";

        // Sending a request and receiving an encrypted response
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

        // Decoding the answer
        ResponseDecoder decoder = new ResponseDecoder(response, getEncryptionServerListKey());
        return decoder.decode();
    }

    private List<Server> getFreeServers(String response) {
        // Transform JSON into only the required blocks
        Gson gson = new Gson();
        ServerResponse serverResponse = gson.fromJson(response, ServerResponse.class);

        // We return only free servers
        return serverResponse.getServers();
    }

    private List<Server> getVipServers(String response) {
        // Transform JSON into only the required blocks
        Gson gson = new Gson();
        ServerResponse serverResponse = gson.fromJson(response, ServerResponse.class);

        // We are returning only VIP servers
        return serverResponse.getVipServers();
    }
}


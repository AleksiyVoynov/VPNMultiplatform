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

        // Отправка запроса и получение зашифрованного ответа
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

        // Расшифровка ответа
        ResponseDecoder decoder = new ResponseDecoder(response, getEncryptionServerListKey());
        return decoder.decode();
    }

    private List<Server> getFreeServers(String response) {
        // Преобразуем JSON только в нужные блоки
        Gson gson = new Gson();
        ServerResponse serverResponse = gson.fromJson(response, ServerResponse.class);

        // Возвращаем только бесплатные серверы
        return serverResponse.getServers();
    }

    private List<Server> getVipServers(String response) {
        // Преобразуем JSON только в нужные блоки
        Gson gson = new Gson();
        ServerResponse serverResponse = gson.fromJson(response, ServerResponse.class);

        // Возвращаем только VIP-серверы
        return serverResponse.getVipServers();
    }

    public static void main(String[] args) throws Exception {
        GetServerLists getServerLists = new GetServerLists();

        List<Server> freeServers = getServerLists.freeServers;
        List<Server> vipServers = getServerLists.vipServers;

        int a = 0;
    }
}


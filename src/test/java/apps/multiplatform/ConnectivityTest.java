package apps.multiplatform;

import apps.BaseTest;
import apps.multiplatform.pages.mainPage.MainScreen;
import apps.multiplatform.pages.serverList.Server;
import apps.multiplatform.utils.ServerUtils;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Epic("Connectivity tests")
public class ConnectivityTest extends BaseTest {

    private List<Server> servers = new ArrayList<>();
    private int clusters;

    @BeforeClass(description = "check all servers in server list", enabled = false)
    public void generateServers3() {
        servers = new MainScreen(customDriver)
                .tapIKEv2()
                .tapFastestLocation()
                .tapFree()
                .serversParsing();

        //write to json if it needed
/*        String filePath = "src/main/java/apps/multiplatform/utils/servers.json";
        new ServerUtils().writeServersToJsonFile(servers, filePath);*/

        clusters = servers.get(servers.size() -1).clusterIndex;

        Allure.addAttachment("number of servers", String.valueOf(servers.size()));
    }

    @BeforeClass(description = "for range check", enabled = false)
    public void generateServers2() {
        servers.add(new Server(0, "ikev2-42 ( 1 )", "ikev2-42"));
        servers.add(new Server(21, "Germany13 ( 4 )", "Germany60"));
        servers.add(new Server(42, "Germany9 ( 3 )", "Germany35"));
        servers.add(new Server(66, "Netherlands3 ( 5 )", "Netherlands14"));
        servers.add(new Server(90, "France1030 ( 5 )", "France147"));
        servers.add(new Server(119, "London ( 5 )", "London4"));
        servers.add(new Server(142, "Singapore3 ( 1 )", "Singapore11"));
        servers.add(new Server(173, "Miami6 ( 5 )", "Miami28"));
        servers.add(new Server(188, "LosAngeles5 ( 2 )", "LosAngeles16"));

        clusters = new MainScreen(customDriver)
                .tapIKEv2()
                .tapFastestLocation()
                .tapFree()
                .getAllClusters().size();

        Allure.addAttachment("number of servers", String.valueOf(servers.size()));
    }

    @BeforeClass(description = "for first 100 servers")
    public void generateServers() throws IOException {
        String filePath = "src/main/java/apps/multiplatform/utils/servers.json";
        servers = new ServerUtils().readServersFromJsonFile(filePath);
        servers = servers.subList(0, 110);

        clusters = new MainScreen(customDriver)
                .tapIKEv2()
                .tapFastestLocation()
                .tapFree()
                .getAllClusters().size();

        Allure.addAttachment("number of servers", String.valueOf(servers.size()));
    }


    @DataProvider(name = "serverData")
    public Object[][] serverData() {
        Object[][] data = new Object[servers.size()][1];
        for (int i = 0; i < servers.size(); i++) {
            data[i][0] = servers.get(i);
        }
        return data;
    }


    @Test(priority = 1, description = "check connection IKEv2", dataProvider = "serverData")
    @Severity(SeverityLevel.CRITICAL)
    @Story("FREE IKEv2")
    @Description("""
            This test checks the availability of the server for the IKEv2 protocol.
            Connection to the server via UI
            Check whether there was a connection to the server""")
    public void Connectivity(Server server) {
        new MainScreen(customDriver)
                .tapIKEv2()
                .tapFastestLocation()
                .tapFree()
                .openCluster(server, clusters)
                .tapServer(server)
                .validateConnection(server)
                .tapBack();
    }
}

package apps.multiplatform;

import apps.BaseTest;
import apps.multiplatform.pages.mainPage.MainScreen;
import apps.multiplatform.pages.serverList.Server;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Epic("Connectivity tests")
public class ConnectivityTest extends BaseTest {

    private List<Server> servers;
    private int clusters;

    @BeforeClass
    public void generateServers() throws IOException {
        servers = new ArrayList<>();
        servers.add(new Server(0, "ikev2-42 ( 1 )", "ikev2-42"));
        servers.add(new Server(21, "Germany13 ( 4 )", "Germany60"));
        servers.add(new Server(42, "Germany9 ( 3 )", "Germany35"));
        servers.add(new Server(66, "Netherlands3 ( 5 )", "Netherlands14"));
        servers.add(new Server(90, "France1030 ( 5 )", "France147"));
        servers.add(new Server(119, "London ( 5 )", "London4"));
        servers.add(new Server(142, "Singapore3 ( 1 )", "Singapore11"));
        servers.add(new Server(173, "Miami6 ( 5 )", "Miami28"));
        servers.add(new Server(188, "LosAngeles5 ( 2 )", "LosAngeles16"));
/*        String filePath = "src/main/java/apps/multiplatform/utils/servers.json";
        servers = new ServerUtils().readServersFromJsonFile(filePath);*/
/*        servers = new MainScreen(customDriver)
                .tapIKEv2()
                .tapFastestLocation()
                .tapFree()
                .serversParsing();

        String filePath = "src/main/java/apps/multiplatform/utils/servers.json";
        new ServerUtils().writeServersToJsonFile(servers, filePath);*/

        //clusters = servers.get(servers.size() -1).clusterIndex;
        clusters = 189;
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

package apps.multiplatform;

import apps.BaseTest;
import apps.multiplatform.pages.mainPage.MainScreen;
import apps.multiplatform.pages.serverList.Server;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Epic("Connectivity tests")
public class ConnectivityTest extends BaseTest {

    private List<Server> servers;

    @BeforeClass
    public void generateServers() {
        servers = new ArrayList<>();
        servers.add(new Server(4, "Sweden3 ( 5 )", "Sweden11"));
        servers.add(new Server(92, "France1032 ( 5 )", "France156"));
        servers.add(new Server(189, "LosAngeles5 ( 2 )", "LosAngeles15"));
/*        String filePath = "src/main/java/apps/multiplatform/utils/servers.json";
        servers = new ServerUtils().readServersFromJsonFile(filePath);*/
/*        servers = new MainScreen(customDriver)
                .tapIKEv2()
                .tapFastestLocation()
                .tapFree()
                .serversParsing();*/

        /*String filePath = "src/main/java/apps/multiplatform/utils/servers.json";
        new ServerUtils().writeServersToJsonFile(servers, filePath);*/

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
                .openCluster(server)
                .tapServer(server)
                .validateConnection(server)
                .tapBack();
    }
}

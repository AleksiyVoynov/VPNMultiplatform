package apps.multiplatform.connectivity.connections;

import apps.BaseTest;
import apps.api.serverList.GetServerLists;
import apps.api.serverList.Server;
import apps.pages.multiplatform.mainPage.MainScreen;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Connectivity tests")
public class VIPServerListTest extends BaseTest {

    private List<Server> servers;

    @BeforeClass(description = "parsing vip server list from api")
    public void precondition() throws Exception {

        servers = new GetServerLists().vipServers;
        servers = servers.subList(0, 101);

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
    @Story("VIP IKEv2")
    @Description("""
            This test checks the availability of the server for the IKEv2 protocol.
            Connection to the server via UI
            Check whether there was a connection to the server""")
    public void Connectivity(Server server) {
        new MainScreen(customDriver)
                .chooseProtocol("IKEv2")
                .tapFastestLocation()
                .tapVip()
                .selectServer(server)
                .validateConnection(server)
                .tapBack();
    }
}

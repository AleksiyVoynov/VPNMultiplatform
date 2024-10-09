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
        servers.add(new Server("Sweden3 ( 5 )", "Sweden11"));
        servers.add(new Server("Sweden3 ( 5 )", "Sweden13"));
        servers.add(new Server("Sweden3 ( 5 )", "Sweden15"));
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
    @Story("IKEv2 FREE")
    @Description("""
            Test Description:
            This test checks the availability of servers for the IKEv2 protocol.
            The check is performed on each server in the list for free user""")
    public void Connectivity(Server server) {
        new MainScreen(customDriver)
                .tapIKEv2()
                .tapFastestLocation()
                .tapFree()
                .openCluster(server.cluster)
                .tapServer(server.name)
                .validateConnectionDetailPage()
                .tapBack();
                //.tapPowerButton();

        /*new ConnectionReport(customDriver)
                .validateConnectionReportPage()
                .tapBack();*/
    }
}

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

    private List<Server> servers;

    @BeforeClass
    public void generateServers() throws IOException {
        String filePath = "src/main/java/apps/multiplatform/utils/servers.json";
        servers = new ServerUtils().readServersFromJsonFile(filePath);
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
            Test Description:
            This test checks the availability of servers for the IKEv2 protocol.
            The check is performed on each server in the list for free user""")
    public void Connectivity(Server server) throws InterruptedException {
        new MainScreen(customDriver)
                .tapIKEv2()
                .tapFastestLocation()
                .tapFree()
                .openCluster(server)
                .tapServer(server)
                .validateConnectionDetailPage(server)
                .tapBack();
                //.tapPowerButton();

        /*new ConnectionReport(customDriver)
                .validateConnectionReportPage()
                .tapBack();*/
    }
}

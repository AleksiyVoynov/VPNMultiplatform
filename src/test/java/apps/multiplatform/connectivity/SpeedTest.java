package apps.multiplatform.connectivity;

import apps.BaseTest;
import apps.common.Browser;
import apps.pages.multiplatform.mainPage.MainScreen;
import apps.pages.multiplatform.serverList.Server;
import apps.pages.web.SpeedTestPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Epic("Speed tests")
public class SpeedTest extends BaseTest {
    private final List<Server> servers = new ArrayList<>();
    private int clusters;

    @BeforeClass(description = "definitions of tested servers in the list")
    public void generateServers2() {
        servers.add(new Server(0, "Norway ( 1 )", "Norway1"));

        clusters = new MainScreen(customDriver)
                .chooseProtocol()
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


    @Test(priority = 1, description = "check connection speed with IKEv2", dataProvider = "serverData")
    @Severity(SeverityLevel.CRITICAL)
    @Story("FREE IKEv2")
    @Description("""
            This test checks the speed of a given server using speed test""")
    public void Connectivity(Server server) {
        String expectedURL = "https://www.google.com/search?q=google+speed+test";
        Browser browser = new Browser(customDriver);
        MainScreen mainScreen = new MainScreen(customDriver);

        mainScreen.chooseProtocol("IKEv2")
                .tapFastestLocation()
                .tapFree()
                .openCluster(server, clusters)
                .tapServer(server)
                .validateConnection(server);

        browser.openMobileBrowser();

        try {
            browser.goToURL(expectedURL);
            Assert.assertTrue(new SpeedTestPage(customDriver).runSpeedTest().mbpsDownload > 10,
                    "the upload speed resul was less then 10 mbps");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            browser.closeBrowser();
        }
    }
}

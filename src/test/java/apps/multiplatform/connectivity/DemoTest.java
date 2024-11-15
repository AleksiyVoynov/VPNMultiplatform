package apps.multiplatform.connectivity;

import apps.BaseTest;
import apps.common.Browser;
import apps.pages.multiplatform.mainPage.MainScreen;
import apps.pages.multiplatform.serverList.Server;
import apps.pages.web.SpeedTestFast;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Epic("Demo tests")
public class DemoTest extends BaseTest {
    private final List<Server> servers = new ArrayList<>();
    private int clusters;
    private Browser browser;

    @BeforeClass(description = "get server list")
    public void generateServers2() {
        servers.add(new Server(0, "Ukraine ( 3 )", "Ukraine1"));
        servers.add(new Server(0, "Ukraine ( 3 )", "Ukraine2"));
        servers.add(new Server(0, "Ukraine ( 3 )", "Ukraine3"));

        clusters = new MainScreen(customDriver)
                .chooseProtocol("IKEv2")
                .tapFastestLocation()
                .tapVip()
                .getAllClusters().size();

        Allure.addAttachment("number of servers", String.valueOf(servers.size()));
    }

    @AfterMethod()
    public void closeBrowser() {
        browser.closeBrowser();
    }


    @DataProvider(name = "serverData")
    public Object[][] serverData() {
        Object[][] data = new Object[servers.size()][1];
        for (int i = 0; i < servers.size(); i++) {
            data[i][0] = servers.get(i);
        }
        return data;
    }


    @Test(priority = 1, description = "check connection speed > 10 Mbps", dataProvider = "serverData")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User: VIP, Protocol: IKEv2")
    @Description("""
            This test checks the speed of a given server using https://fast.com/
            The test result should be more the 10 mbps""")
    public void Connectivity10(Server server) {
        String expectedURL = "https://fast.com";
        browser = new Browser(customDriver);
        MainScreen mainScreen = new MainScreen(customDriver);

        mainScreen.chooseProtocol("IKEv2")
                .tapFastestLocation()
                .tapVip()
                .openCluster(server, clusters)
                .tapServer(server)
                .validateConnection(server);

        browser.openMobileBrowser();

        browser.goToURL(expectedURL);
        double result = new SpeedTestFast(customDriver).runSpeedTest();
        Assert.assertTrue(result > 10,
                "the upload speed result (" + result + ") was less then 10 Mbps");
    }

    @Test(priority = 1, description = "check connection speed >= 100 Mbps")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User: VIP, Protocol: IKEv2")
    @Description("""
            This test checks the speed of a given server using https://fast.com/
            The test result should be more the 100 Mbps""")
    public void Connectivity100() {
        Server server = servers.get(0);
        String expectedURL = "https://fast.com";
        browser = new Browser(customDriver);
        MainScreen mainScreen = new MainScreen(customDriver);

        mainScreen.chooseProtocol("IKEv2")
                .tapFastestLocation()
                .tapVip()
                .openCluster(server, clusters)
                .tapServer(server)
                .validateConnection(server);

        browser.openMobileBrowser();

        browser.goToURL(expectedURL);
        double result = new SpeedTestFast(customDriver).runSpeedTest();
        Assert.assertTrue(result > 100,
                "the upload speed result (" + result + ") was less then 100 Mbps");
    }
}

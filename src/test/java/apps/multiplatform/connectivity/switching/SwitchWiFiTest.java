package apps.multiplatform.connectivity.switching;

import apps.BaseTest;
import apps.common.Browser;
import apps.common.ToggleWiFI;
import apps.multiplatform.pages.connection.ConnectionDetail;
import apps.multiplatform.pages.mainPage.MainScreen;
import apps.multiplatform.pages.serverList.Server;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

@Epic("Connectivity tests")
@Feature("WIFI Switching")
public class SwitchWiFiTest extends BaseTest {

    private final Server server = new Server(3, "Sweden27 ( 3 )", "Sweden130");
    private int clusters;
    private final String expectedURL = "https://m.vk.com";

    @BeforeClass()
    public void precondition() {

        clusters = new MainScreen(customDriver)
                .chooseProtocol()
                .tapFastestLocation()
                .tapFree()
                .getAllClusters().size();
    }

    @DataProvider(name = "protocolData")
    public Object[][] protocolData() {
        return new Object[][] {
                { "IKEv2" },
                { "OpenVPNTCP" },
                { "OpenVPNUDP" },
                { "Super" }
        };
    }


    @Test(priority = 1, description = "wi-fi off (5 min)", dataProvider = "protocolData")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Turn off/on wifi 5 min")
    @Description("""
            The purpose of the test is to check the connection after turning off the Wi-Fi (5 minutes)""")
    public void Connectivity5(String protocol) {
        ConnectionDetail connectionDetail = new ConnectionDetail(customDriver);
        ToggleWiFI toggleWiFI = new ToggleWiFI(customDriver);
        Browser browser = new Browser(customDriver);
        MainScreen mainScreen = new MainScreen(customDriver);

        mainScreen
                .chooseProtocol(protocol)
                .tapFastestLocation()
                .tapFree()
                .openCluster(server, clusters)
                .tapServer(server)
                .validateConnectionDetailPage(server);

        toggleWiFI.wifiOFF();

        connectionDetail
                .validateConnectionDetailPage(server)
                .pause(Duration.ofMinutes(5));

        toggleWiFI.wifiON();

        connectionDetail
                .validateConnectionDetailPage(server);

        browser.openMobileBrowser();//todo steps

        try {
            browser.goToURL(expectedURL);
        } catch (org.openqa.selenium.WebDriverException ignored) {
            Assert.fail(expectedURL +" page didn't open" );
        } finally {
            browser.closeBrowser();
        }

        mainScreen.tapDisconnectButton();
    }

    @Test(priority = 2, description = "wi-fi off (10 min)", dataProvider = "protocolData")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Turn off/on wifi 10 min")
    @Description("""
            The purpose of the test is to check the connection after turning off the Wi-Fi (10 minutes)""")
    public void Connectivity10(String protocol) {
        ConnectionDetail connectionDetail = new ConnectionDetail(customDriver);
        ToggleWiFI toggleWiFI = new ToggleWiFI(customDriver);
        Browser browser = new Browser(customDriver);
        MainScreen mainScreen = new MainScreen(customDriver);

        mainScreen
                .chooseProtocol(protocol)
                .tapFastestLocation()
                .tapFree()
                .openCluster(server, clusters)
                .tapServer(server)
                .validateConnectionDetailPage(server);

        toggleWiFI.wifiOFF();

        connectionDetail
                .validateConnectionDetailPage(server)
                .pause(Duration.ofMinutes(10));

        toggleWiFI.wifiON();

        connectionDetail
                .validateConnectionDetailPage(server);

        browser.openMobileBrowser();

        try {
            browser.goToURL(expectedURL);
        } catch (org.openqa.selenium.WebDriverException ignored) {
            Assert.fail(expectedURL +" page didn't open" );
        } finally {
            browser.closeBrowser();
        }

        mainScreen.tapDisconnectButton();
    }

    @Test(priority = 3, description = "wi-fi off (20 min)", dataProvider = "protocolData")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Turn off/on wifi 20 min")
    @Description("""
            The purpose of the test is to check the connection after turning off the Wi-Fi (20 minutes)""")
    public void Connectivity20(String protocol) {
        ConnectionDetail connectionDetail = new ConnectionDetail(customDriver);
        ToggleWiFI toggleWiFI = new ToggleWiFI(customDriver);
        Browser browser = new Browser(customDriver);
        MainScreen mainScreen = new MainScreen(customDriver);

        mainScreen
                .chooseProtocol(protocol)
                .tapFastestLocation()
                .tapFree()
                .openCluster(server, clusters)
                .tapServer(server)
                .validateConnectionDetailPage(server);

        toggleWiFI.wifiOFF();

        connectionDetail
                .validateConnectionDetailPage(server)
                .pause(Duration.ofMinutes(20));

        toggleWiFI.wifiON();

        connectionDetail
                .validateConnectionDetailPage(server);

        browser.openMobileBrowser();

        try {
            browser.goToURL(expectedURL);
        } catch (org.openqa.selenium.WebDriverException ignored) {
            Assert.fail(expectedURL +" page didn't open" );
        } finally {
            browser.closeBrowser();
        }

        mainScreen.tapDisconnectButton();
    }
}


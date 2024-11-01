package apps.multiplatform.connectivity.switching;

import apps.BaseTest;
import apps.common.Browser;
import apps.common.ToggleLTE;
import apps.common.ToggleWiFI;
import apps.pages.multiplatform.connection.ConnectionDetail;
import apps.pages.multiplatform.mainPage.MainScreen;
import apps.pages.multiplatform.serverList.Server;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

@Epic("Connectivity tests")
@Feature("LTE Switching")
public class SwitchLTEMobileTest extends BaseTest {

    private final Server server = new Server(3, "Sweden27 ( 3 )", "Sweden130");
    private int clusters;
    private final String expectedURL = "https://m.vk.com";

    @BeforeClass()
    public void precondition() {
        new ToggleWiFI(customDriver).wifiOFF();

        clusters = new MainScreen(customDriver)
                .chooseProtocol()
                .tapFastestLocation()
                .tapFree()
                .getAllClusters().size();
    }

    @AfterClass()
    public void afterCondition() {
        new ToggleWiFI(customDriver).wifiON();
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


    @Test(priority = 1, description = "lte off (5 min)", dataProvider = "protocolData")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Turn off/on lte 5 min")
    @Description("""
            The purpose of the test is to check the connection after turning off the lte (5 minutes)""")
    public void Connectivity5(String protocol) {
        ConnectionDetail connectionDetail = new ConnectionDetail(customDriver);
        ToggleLTE toggleLTE = new ToggleLTE(customDriver);
        Browser browser = new Browser(customDriver);
        MainScreen mainScreen = new MainScreen(customDriver);

        mainScreen
                .chooseProtocol(protocol)
                .tapFastestLocation()
                .tapFree()
                .openCluster(server, clusters)
                .tapServer(server)
                .validateConnectionDetailPage(server);

        toggleLTE.lteOFF();

        connectionDetail
                .validateConnectionDetailPage(server)
                .pause(Duration.ofMinutes(5));

        toggleLTE.lteON();

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

    @Test(priority = 2, description = "lte off (10 min)", dataProvider = "protocolData")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Turn off/on lte 10 min")
    @Description("""
            The purpose of the test is to check the connection after turning off the lte (10 minutes)""")
    public void Connectivity10(String protocol) {
        ConnectionDetail connectionDetail = new ConnectionDetail(customDriver);
        ToggleLTE toggleLTE = new ToggleLTE(customDriver);
        Browser browser = new Browser(customDriver);
        MainScreen mainScreen = new MainScreen(customDriver);

        mainScreen
                .chooseProtocol(protocol)
                .tapFastestLocation()
                .tapFree()
                .openCluster(server, clusters)
                .tapServer(server)
                .validateConnectionDetailPage(server);

        toggleLTE.lteOFF();

        connectionDetail
                .validateConnectionDetailPage(server)
                .pause(Duration.ofMinutes(10));

        toggleLTE.lteON();

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

    @Test(priority = 3, description = "lte off (20 min)", dataProvider = "protocolData")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Turn off/on lte 20 min")
    @Description("""
            The purpose of the test is to check the connection after turning off the lte (20 minutes)""")
    public void Connectivity20(String protocol) {
        ConnectionDetail connectionDetail = new ConnectionDetail(customDriver);
        ToggleLTE toggleLTE = new ToggleLTE(customDriver);
        
        Browser browser = new Browser(customDriver);
        MainScreen mainScreen = new MainScreen(customDriver);

        mainScreen
                .chooseProtocol(protocol)
                .tapFastestLocation()
                .tapFree()
                .openCluster(server, clusters)
                .tapServer(server)
                .validateConnectionDetailPage(server);

        toggleLTE.lteOFF();

        connectionDetail
                .validateConnectionDetailPage(server)
                .pause(Duration.ofMinutes(20));

        toggleLTE.lteON();

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


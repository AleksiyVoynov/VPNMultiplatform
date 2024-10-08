package apps.multiplatform;

import apps.BaseTest;
import apps.multiplatform.pages.mainPage.MainScreen;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Epic("Connectivity tests")
public class ConnectivityTest extends BaseTest {

    private List<String> serverList;

    @BeforeClass
    public void generateServers() {
        serverList = new ArrayList<>();
        serverList.add("German 32");
        serverList.add("USA 7");
        serverList.add("Ukraine 3");
    }

    @DataProvider(name = "serverData")
    public Object[][] serverData() {
        Object[][] data = new Object[serverList.size()][1];
        for (int i = 0; i < serverList.size(); i++) {
            data[i][0] = serverList.get(i);
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
    public void Connectivity(String server) {
        new MainScreen(customDriver)
                .tapIKEv2()
                .tapFastestLocation()
                .tapFree()
                .tapFastestLocation()
                .validateConnectionDetailPage()
                .tapBack();
                //.tapPowerButton();

        /*new ConnectionReport(customDriver)
                .validateConnectionReportPage()
                .tapBack();*/
    }
}

package apps.vpn.freeUser;

import apps.BaseTest;
import common.Contexts;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.apps.vpn.MainScreen;
import pages.apps.vpn.ads.NativeADS;

public class Temp extends BaseTest {

    @Test(priority = 1, description = "some")
    @Description("""
            Test Description:
            1. some
            2. some
            3. some""")
    public void validationElements() {
        MainScreen mainScreen = new MainScreen(appiumDriver);
        appiumDriver.findElement(new NativeADS().close).click();
        new Contexts(config, appiumDriver).webViewContext();
        Assert.fail();
        
    }
}

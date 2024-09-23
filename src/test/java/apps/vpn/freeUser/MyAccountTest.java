package apps.vpn.freeUser;

import apps.BaseTest;
import common.Contexts;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.apps.vpn.LogIn;
import pages.banners.Banner;
import pages.banners.ReNewNowBanner;
import pages.apps.vpn.MainScreen;
import pages.apps.vpn.Menu;

import java.time.Duration;

public class MyAccountTest extends BaseTest {

    @Test(priority = 1, description = "validation elements")
    @Description("""
            Test Description:
            1. go to app
            2. open menu
            3. validate page elements""")
    public void validationElements() {
        MainScreen mainScreen = new MainScreen(appiumDriver);
        mainScreen.fluentVisibility(mainScreen.menu.menuButton ,Duration.ofSeconds(20)).click();
        appiumDriver.findElement(mainScreen.menu.signIn).click();
        new LogIn(config, appiumDriver).logIn("nagaraju.batchu7+webExpiredMonthly@gmail.com", "Test@123");
        Assert.fail();
    }
}

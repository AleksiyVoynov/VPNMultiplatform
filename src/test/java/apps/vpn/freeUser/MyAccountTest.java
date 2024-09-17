package apps.vpn.freeUser;

import apps.BaseTest;
import common.Contexts;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
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
        Menu menu = new Menu(appiumDriver);
        new Contexts(config, appiumDriver).webViewContext();
        mainScreen.fluentVisibility(mainScreen.menuButton ,Duration.ofSeconds(3)).click();
        Banner banner = new ReNewNowBanner(appiumDriver);
        appiumDriver.findElement(menu.myAccount).click();
        Assert.fail();
    }
}

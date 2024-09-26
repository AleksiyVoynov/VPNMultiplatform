package apps.vpn.freeUser;

import apps.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.apps.vpn.LogIn;
import pages.apps.vpn.MainScreen;

import java.time.Duration;

public class MyAccountTest extends BaseTest {

    @Test(priority = 1, description = "validation elements")
    @Description("""
            Test Description:
            1. go to app
            2. open menu
            3. validate page elements""")
    public void validationElements() {
        MainScreen mainScreen = new MainScreen(customDriver);
        mainScreen.fluentVisibility(mainScreen.menu.menuButton ,Duration.ofSeconds(20)).click();
        customDriver.getAppiumDriver().findElement(mainScreen.menu.signIn).click();
        new LogIn(customDriver).logIn("nagaraju.batchu7+iOSYearlyExpired@gmail.com", "Test@123");
        Assert.fail();
    }
}

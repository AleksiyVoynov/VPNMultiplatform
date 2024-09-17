package apps.vpn.freeUser;

import apps.BaseTest;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.apps.vpn.MainScreen;

import java.time.Duration;

public class MainScreenTest extends BaseTest {

    @Test(priority = 1, description = "validation page")
    @Description("""
            Test Description:
            1. go to app
            2. parsing page elements
            3. validate result""")
    public void validationElements() {
        MainScreen mainScreen = new MainScreen(appiumDriver);
        WebElement element = mainScreen.fluentVisibility(mainScreen.connectButton ,Duration.ofSeconds(60));
        Assert.fail();
        
    }
}

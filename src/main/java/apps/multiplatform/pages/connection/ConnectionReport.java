package apps.multiplatform.pages.connection;

import driver.CustomDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ConnectionReport extends Connection{

    public final By titleConnectionReport = AppiumBy.androidUIAutomator("new UiSelector().text(\"Connection Report\")");

    //share block
    public final By titleSupport = AppiumBy.androidUIAutomator("new UiSelector().text(\"Support us with a\n" +
            "5-star rating!\")");
    public final By subTitleShare = By.id("com.free.vpn.super.hotspot.open:id/tv_sub_title");
    public final By ratingBar = By.id("com.free.vpn.super.hotspot.open:id/rating_bar");

    public ConnectionReport(CustomDriver customDriver) {
        super(customDriver);
    }

    @Step("validate connection report page")
    public ConnectionReport validateConnectionReportPage() {
        Assert.assertEquals(fluentVisibility(titleConnectionReport).getText(), "Connection Report", "title page was incorrect");
        Assert.assertEquals(fluentVisibility(youAreConnectedText).getText(), "You are disconnected", "title page was incorrect");
        Assert.assertTrue(customDriver.getAppiumDriver().findElement(timer).isDisplayed());
        return new ConnectionReport(customDriver);
    }
}

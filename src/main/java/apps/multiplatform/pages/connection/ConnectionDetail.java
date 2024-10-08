package apps.multiplatform.pages.connection;

import driver.CustomDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ConnectionDetail extends Connection {

    public final By titleConnectionDetail =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Connection Details\")");

    //share block
    public final By titleShare = AppiumBy.androidUIAutomator("new UiSelector().text(\"Share With Friends\")");
    public final By subTitleShare = By.id("com.free.vpn.super.hotspot.open:id/tv_sub_title");
    public final By shareButton = By.id("com.free.vpn.super.hotspot.open:id/btn_action");

    public ConnectionDetail(CustomDriver customDriver) {
        super(customDriver);
    }

    @Step("validate connection detail page")
    public ConnectionDetail validateConnectionDetailPage() {
        Assert.assertEquals(fluentVisibility(titleConnectionDetail).getText(),
                "Connection Details", "title page was incorrect");
        Assert.assertEquals(fluentVisibility(youAreConnectedText).getText(),
                "You are connected", "title page was incorrect");
        Assert.assertTrue(customDriver.getAppiumDriver().findElement(timer).isDisplayed());
        attachScreenToReport("connection details", By.id("com.free.vpn.super.hotspot.open:id/layout_ip_info"));
        return new ConnectionDetail(customDriver);
    }
}

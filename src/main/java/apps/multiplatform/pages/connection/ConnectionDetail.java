package apps.multiplatform.pages.connection;

import apps.multiplatform.pages.serverList.Server;
import driver.CustomDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Allure;
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
    public ConnectionDetail validateConnectionDetailPage(Server server) {

        // title block
        Assert.assertEquals(fluentVisibility(titleConnectionDetail).getText(),
                "Connection Details", "title page was incorrect");

        // timer block
        Assert.assertEquals(fluentVisibility(youAreConnectedText).getText(),
                "You are connected", "title page was incorrect");
        Assert.assertTrue(appiumDriver.findElement(timer).isDisplayed(), "timer didn't displayed");

        // connection details block
        Assert.assertTrue(appiumDriver.findElement(countryFlag).isDisplayed(), "flag didn't displayed");
        Assert.assertEquals(appiumDriver.findElement(vpnServer).getText(),
                server.name, "server name was incorrect");
        Assert.assertEquals(appiumDriver.findElement(titleVpnIp).getText(),
                "VPN IP:", "VPN IP label was incorrect");
        String vpnIp = appiumDriver.findElement(ip).getText();
        Assert.assertFalse(vpnIp.isEmpty(), "VPN IP didn't displayed");

        Assert.assertEquals(appiumDriver.findElement(titleCity).getText(),
                "City:", "city label was incorrect");
        String cityText = appiumDriver.findElement(city).getText();
        Assert.assertFalse(cityText.isEmpty(), "city didn't displayed");

        Assert.assertEquals(appiumDriver.findElement(titleCountry).getText(),
                "Country:", "country label was incorrect");
        String countryText = appiumDriver.findElement(country).getText();
        Assert.assertFalse(countryText.isEmpty(), "country didn't displayed");

        // share block
        String attachmentTest = """
        VPN IP: %s
        City: %s
        Country: %s
        """.formatted(vpnIp, cityText, countryText);

        Allure.addAttachment("connection details text", attachmentTest);
        attachScreenToReport("connection details screen", By.id("com.free.vpn.super.hotspot.open:id/layout_ip_info"));

        return new ConnectionDetail(customDriver);
    }

}

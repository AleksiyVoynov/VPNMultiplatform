package pages.apps.vpn;

import configs.Config;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.List;

public class MainScreen extends BasePage {
    public final By menuButton = By.id("com.free.vpn.super.hotspot.open:id/btnMenu");
    public final By crownButton = By.id("com.free.vpn.super.hotspot.open:id/btnPremium");
    public final By locationButton = By.id("com.free.vpn.super.hotspot.open:id/btnLocation");
    public final By browserButton = By.id("com.free.vpn.super.hotspot.open:id/btnBrowser");
    public final By helpButton = By.id("com.free.vpn.super.hotspot.open:id/btnHelp");
    public final By serverListLink = By.id("com.free.vpn.super.hotspot.open:id/tv_fastest_server");
    public final By connectButton = By.id("com.free.vpn.super.hotspot.open:id/iv_connect");
    public final By modesButtons = AppiumBy.androidUIAutomator("new UiSelector().text(\"Auto\")");
    public final By protocolAuto = AppiumBy.androidUIAutomator("new UiSelector().text(\"IKEv2\")");
    public final By protocolIKEv2 = AppiumBy.androidUIAutomator("new UiSelector().text(\"IKEv2\")");



    public MainScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @Step("make search {0}")
    public void searchFor() {
    }
}

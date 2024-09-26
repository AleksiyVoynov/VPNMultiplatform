package pages.apps.vpn;

import driver.CustomDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.BasePage;

public class MainScreen extends BasePage {
    public Menu menu;
    public final By crownButton = By.id("com.free.vpn.super.hotspot.open:id/btnPremium");
    public final By locationButton = By.id("com.free.vpn.super.hotspot.open:id/btnLocation");
    public final By browserButton = By.id("com.free.vpn.super.hotspot.open:id/btnBrowser");
    public final By helpButton = By.id("com.free.vpn.super.hotspot.open:id/btnHelp");
    public final By serverListLink = By.id("com.free.vpn.super.hotspot.open:id/tv_fastest_server");
    public final By connectButton = By.id("com.free.vpn.super.hotspot.open:id/iv_connect");
    public final By modesButtons = AppiumBy.androidUIAutomator("new UiSelector().text(\"Auto\")");
    public final By protocolAuto = AppiumBy.androidUIAutomator("new UiSelector().text(\"IKEv2\")");
    public final By protocolIKEv2 = AppiumBy.androidUIAutomator("new UiSelector().text(\"IKEv2\")");



    public MainScreen(CustomDriver customDriver) {
        super(customDriver);
        menu = new Menu(this.customDriver);
    }

    @Step("make search {0}")
    public void searchFor() {
    }
}

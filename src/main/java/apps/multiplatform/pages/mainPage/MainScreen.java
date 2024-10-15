package apps.multiplatform.pages.mainPage;

import apps.multiplatform.BasePage;
import apps.multiplatform.pages.menu.Menu;
import apps.multiplatform.pages.serverList.ServerList;
import driver.CustomDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

public class MainScreen extends BasePage {
    public Menu menu;
    public final By crownButton = By.id("com.free.vpn.super.hotspot.open:id/btnPremium");
    public final By locationButton = By.id("com.free.vpn.super.hotspot.open:id/btnLocation");
    public final By browserButton = By.id("com.free.vpn.super.hotspot.open:id/btnBrowser");
    public final By helpButton = By.id("com.free.vpn.super.hotspot.open:id/btnHelp");
    public final By serverListLink = By.id("com.free.vpn.super.hotspot.open:id/tv_fastest_server");
    public final By connectButton = By.id("com.free.vpn.super.hotspot.open:id/iv_connect");
    public final By connectStatus = By.id("com.free.vpn.super.hotspot.open:id/tvConnectStatus");
    public final By modesButtons = AppiumBy.androidUIAutomator("new UiSelector().text(\"Auto\")");
    public final By protocolAuto = AppiumBy.androidUIAutomator("new UiSelector().text(\"IKEv2\")");
    public final By protocolIKEv2 = AppiumBy.androidUIAutomator("new UiSelector().text(\"IKEv2\")");


    public MainScreen(CustomDriver customDriver) {
        super(customDriver);
        menu = new Menu();

        try {
            Menu menu = new Menu();
            appiumDriver.findElement(menu.mainNavView);
            appiumDriver.findElement(menu.backButton).click();
        } catch (org.openqa.selenium.NoSuchElementException ignored) {

        }

        try {
            fluentVisibility(connectButton, Duration.ofSeconds(1));
        } catch (TimeoutException ignored) {
            appiumDriver.navigate().back();
            fluentVisibility(connectButton, Duration.ofSeconds(1));
        }
    }

    @Step("tap on IKEv2 protocol")
    public MainScreen tapIKEv2() {
        fluentVisibility(protocolIKEv2).click();
        return this;
    }

    @Step("tap fastest location")
    public ServerList tapFastestLocation() {
        fluentToBeClickable(serverListLink).click();
        return new ServerList(customDriver);
    }

    @Step("tap power button")
    public void tapPowerButton() {
        if (fluentVisibility(connectStatus).getText().equals("CONNECTED")) {
            tapDisconnectButton();
        } else {
            tapConnectButton();
        }
    }

    @Step("tap connect button")
    private void tapConnectButton() {
        fluentVisibility(connectButton).click();
    }

    @Step("tap disconnect button")
    private void tapDisconnectButton() {
        fluentVisibility(connectButton).click();
        fluentVisibility(new Dialog().disconnectButton).click();
    }
}

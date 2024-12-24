package apps.multiplatform.pages.multiplatform.mainPage;

import apps.multiplatform.pages.BasePage;
import apps.multiplatform.pages.multiplatform.menu.Menu;
import apps.multiplatform.pages.multiplatform.serverList.ServerList;
import driver.CustomDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

public class MainScreen extends BasePage {
    public final By menuButton = By.id("com.free.vpn.super.hotspot.open:id/btnMenu");
    public final By crownButton = By.id("com.free.vpn.super.hotspot.open:id/btnPremium");
    public final By locationButton = By.id("com.free.vpn.super.hotspot.open:id/btnLocation");
    public final By browserButton = By.id("com.free.vpn.super.hotspot.open:id/btnBrowser");
    public final By helpButton = By.id("com.free.vpn.super.hotspot.open:id/btnHelp");

    public final By serverListLink = By.id("com.free.vpn.super.hotspot.open:id/tv_fastest_server");
    public final By connectButton = By.id("com.free.vpn.super.hotspot.open:id/iv_connect");
    public final By connectStatus = By.id("com.free.vpn.super.hotspot.open:id/tvConnectStatus");

    public final By protocolAuto = AppiumBy.androidUIAutomator("new UiSelector().text(\"Auto\")");
    public final By protocolIKEv2 = AppiumBy.androidUIAutomator("new UiSelector().text(\"IKEv2\")");
    public final By protocolOpenVPNTCP = AppiumBy.androidUIAutomator("new UiSelector().text(\"OpenVPN TCP\")");
    public final By protocolOpenVPNUDP = AppiumBy.androidUIAutomator("new UiSelector().text(\"OpenVPN UDP\")");
    public final By protocolSuper = AppiumBy.androidUIAutomator("new UiSelector().text(\"Super\")");


    public MainScreen(CustomDriver customDriver) {
        super(customDriver);

        try {
            Menu menu = new Menu(customDriver);
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
    @Step("tap on Auto protocol")
    public MainScreen chooseProtocol() {
        fluentVisibility(protocolAuto).click();
        return this;
    }

    @Step("tap on protocol")
    public MainScreen chooseProtocol(String protocol) {
        switch (protocol) {
            case "IKEv2":
                fluentVisibility(protocolIKEv2).click();
                break;

            case "OpenVPNTCP":
                fluentVisibility(protocolOpenVPNTCP).click();
                break;

            case "OpenVPNUDP":
                fluentVisibility(protocolOpenVPNUDP).click();
                break;

            case "Super":
                fluentVisibility(protocolSuper).click();
                break;

            default:
                throw new IllegalArgumentException("Unknown protocol " + protocol);
        }

/*        try {
            WebElement reconnectButton = appiumDriver.findElement(new Dialog().reconnectButton);
            if (reconnectButton.isDisplayed()) {
                reconnectButton.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Reconnect button not found, skipping.");
        }*/
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
    public void tapDisconnectButton() {
        fluentVisibility(connectButton).click();
        fluentVisibility(new Dialog().disconnectButton).click();
    }

    @Step("open menu")
    public Menu openMenu() {
        fluentVisibility(menuButton).click();
        return new Menu(customDriver);
    }
}

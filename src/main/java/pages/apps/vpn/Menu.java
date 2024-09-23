package pages.apps.vpn;

import configs.Config;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.BasePage;

public class Menu extends BasePage {
    public final By menuButton = By.id("com.free.vpn.super.hotspot.open:id/btnMenu");
    public final By signIn = By.id("com.free.vpn.super.hotspot.open:id/menu_sign_in_button");

    public final By myAccount = AppiumBy.androidUIAutomator("new UiSelector().text(\"My Account\")");
    public final By share = AppiumBy.androidUIAutomator("new UiSelector().text(\"Share\")");
    public final By splitTunnelling = AppiumBy.androidUIAutomator("new UiSelector().text(\"Split Tunnelling\")");
    public final By rateUs = AppiumBy.androidUIAutomator("new UiSelector().text(\"Rate us\")");
    public final By aboutUs = AppiumBy.androidUIAutomator("new UiSelector().text(\"About us\")");
    public final By allowNotification = AppiumBy.androidUIAutomator("com.free.vpn.super.hotspot.open:id/allow_notification");

    public Menu(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
}

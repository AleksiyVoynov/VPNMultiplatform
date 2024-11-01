package apps.pages.multiplatform.menu;

import apps.pages.BasePage;
import apps.pages.multiplatform.login.LogIn;
import apps.pages.multiplatform.mainPage.MainScreen;
import com.google.common.collect.ImmutableMap;
import configs.devices.Android;
import configs.devices.IOS;
import driver.CustomDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Menu extends BasePage {
    private final CustomDriver customDriver;

    public final By mainNavView = By.id("com.free.vpn.super.hotspot.open:id/mainNavView");
    public final By backButton = By.id("com.free.vpn.super.hotspot.open:id/button_close");
    public final By signIn = By.id("com.free.vpn.super.hotspot.open:id/menu_sign_in_button");

    public final By myAccount = AppiumBy.androidUIAutomator("new UiSelector().text(\"My Account\")");
    public final By share = AppiumBy.androidUIAutomator("new UiSelector().text(\"Share\")");
    public final By splitTunnelling = AppiumBy.androidUIAutomator("new UiSelector().text(\"Split Tunnelling\")");
    public final By rateUs = AppiumBy.androidUIAutomator("new UiSelector().text(\"Rate us\")");
    public final By aboutUs = AppiumBy.androidUIAutomator("new UiSelector().text(\"About us\")");
    public final By allowNotification = AppiumBy.androidUIAutomator("com.free.vpn.super.hotspot.open:id/allow_notification");

    public Menu(CustomDriver customDriver) {
        super(customDriver);
        this.customDriver = customDriver;
    }

    @Step("sign in")
    public MainScreen singIn(String login, String password) {
        if (customDriver.getDevice() instanceof Android) {
            appiumDriver.executeScript("mobile: shell", ImmutableMap.of("command", "am force-stop com.android.chrome"));
        } else if (customDriver.getDevice() instanceof IOS) {
            System.out.println("you should implement whet it will be needed");
        }

        fluentVisibility(signIn).click();
        new LogIn(customDriver).logIn(login, password);
        return new MainScreen(customDriver);
    }
}

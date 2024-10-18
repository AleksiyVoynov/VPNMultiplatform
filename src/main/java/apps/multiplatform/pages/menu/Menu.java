package apps.multiplatform.pages.menu;

import apps.multiplatform.BasePage;
import apps.multiplatform.pages.login.LogIn;
import apps.multiplatform.pages.mainPage.MainScreen;
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
        fluentVisibility(signIn).click();
        new LogIn(customDriver).logIn(login, password);
        return new MainScreen(customDriver);
    }
}

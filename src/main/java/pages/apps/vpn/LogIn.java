package pages.apps.vpn;

import common.Contexts;
import configs.Config;
import configs.devices.Android;
import configs.devices.IOS;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.BasePage;

public class LogIn extends BasePage {

    private final AppiumDriver appiumDriver;
    private Contexts contexts;
    public final By email = By.cssSelector("#SignInEmailField");
    public final By password = By.cssSelector("#SignInPasswordField");
    public final By logInButton = By.cssSelector("#SignInButton");
    public final By closePage = By.id("com.android.chrome:id/close_button");
    public final By forgotPasswordButton = By.cssSelector("[name='UpdatePasswordModalButton']");

    public LogIn(Config config, AppiumDriver appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
        if (config.device instanceof Android) {
            this.contexts = new Contexts(config, appiumDriver, "WEBVIEW_chrome");
        } else if (config.device instanceof IOS) {
            this.contexts = new Contexts(config, appiumDriver, "safari");
        }
    }

    @Step("make login")
    public void logIn(String email, String password) {
        contexts.webViewContext();
        fluentVisibility(this.email).sendKeys(email);
        fluentVisibility(this.password).sendKeys(password);
        appiumDriver.findElement(this.logInButton).click();
        contexts.nativeContext();
    }
}

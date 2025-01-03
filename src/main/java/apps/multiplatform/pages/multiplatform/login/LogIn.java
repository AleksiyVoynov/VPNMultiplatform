package apps.multiplatform.pages.multiplatform.login;

import apps.multiplatform.pages.BasePage;
import apps.common.Contexts;
import configs.devices.Android;
import configs.devices.IOS;
import driver.CustomDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

public class LogIn extends BasePage {

    private Contexts contexts;
    public final By email = By.cssSelector("#SignInEmailField");
    public final By password = By.cssSelector("#SignInPasswordField");
    public final By logInButton = By.cssSelector("#SignInButton");
    public final By closePage = By.id("com.android.chrome:id/close_button");
    public final By forgotPasswordButton = By.cssSelector("[name='UpdatePasswordModalButton']");

    public LogIn(CustomDriver customDriver) {
        super(customDriver);
        if (customDriver.getDevice() instanceof Android) {
            this.contexts = new Contexts(customDriver, "WEBVIEW_chrome");
        } else if (customDriver.getDevice() instanceof IOS) {
            this.contexts = new Contexts(customDriver, "safari");
        }
    }

    @Step("make login")
    public void logIn(User user) {
        contexts.webViewContext(Duration.ofSeconds(5));
        fluentVisibility(this.email).sendKeys(user.email);
        fluentVisibility(this.password).sendKeys(user.password);
        customDriver.getAppiumDriver().findElement(this.logInButton).click();
        contexts.nativeContext();
    }
}

package pages;

import com.google.common.collect.ImmutableMap;
import configs.Config;
import configs.devices.Android;
import configs.devices.IOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class BasePage {
    protected Config config;

    public BasePage(Config config) {
        this.config = config;
        nativeContext();
    }

    /*public void webViewContext() {
        if (config.device instanceof Android) {
            ((SupportsContextSwitching) config.appiumDriver).context(new ChromeApp(getContext()).webViewContext);
        } else if (config.device instanceof IOS) {
            ((SupportsContextSwitching) config.appiumDriver).context(new SafariApp(getContext()).webViewContext);
        }
    }*/
    /*    private List<String> getContext() {
        return new ArrayList<>(((SupportsContextSwitching) config.appiumDriver).getContextHandles());
    }*/

    public void nativeContext() {
        if (config.device instanceof Android) {
            config.appiumDriver.execute(DriverCommand.SWITCH_TO_CONTEXT, ImmutableMap.of("name", "NATIVE_APP"));
        } else if (config.device instanceof IOS){
            ((SupportsContextSwitching) config.appiumDriver).context("NATIVE_APP");
        }
    }

    public WebElement fluentVisibility(By by, Duration timeout) {

        int duration = 500;

        FluentWait<AppiumDriver> fluentWait = new FluentWait<>(config.appiumDriver).withTimeout(timeout)
                .pollingEvery(Duration.ofMillis(duration))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}

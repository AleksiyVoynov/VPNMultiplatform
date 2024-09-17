package pages;

import com.google.common.collect.ImmutableMap;
import common.Contexts;
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
import java.util.ArrayList;
import java.util.List;

public class BasePage {
    private final AppiumDriver appiumDriver;

    public BasePage(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public WebElement fluentVisibility(By by, Duration timeout) {

        int duration = 500;

        FluentWait<AppiumDriver> fluentWait = new FluentWait<>(appiumDriver).withTimeout(timeout)
                .pollingEvery(Duration.ofMillis(duration))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}

package pages;

import driver.CustomDriver;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class BasePage {
    public final CustomDriver customDriver;

    public BasePage(CustomDriver customDriver) {
        this.customDriver = customDriver;
    }

    public WebElement fluentVisibility(By by, Duration timeout) {

        int duration = 500;

        FluentWait<AppiumDriver> fluentWait = new FluentWait<>(customDriver.getAppiumDriver()).withTimeout(timeout)
                .pollingEvery(Duration.ofMillis(duration))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement fluentVisibility(By by) {

        int duration = 500;

        FluentWait<AppiumDriver> fluentWait = new FluentWait<>(customDriver.getAppiumDriver()).withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofMillis(duration))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}

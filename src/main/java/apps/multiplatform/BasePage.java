package apps.multiplatform;

import configs.devices.IOS;
import driver.CustomDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class BasePage {
    public final CustomDriver customDriver;
    public final AppiumDriver appiumDriver;

    public BasePage(CustomDriver customDriver) {
        this.customDriver = customDriver;
        this.appiumDriver = customDriver.getAppiumDriver();
    }

    public WebElement fluentVisibility(By by, Duration timeout) {

        int duration = 500;

        FluentWait<AppiumDriver> fluentWait = new FluentWait<>(appiumDriver)
                .withTimeout(timeout)
                .pollingEvery(Duration.ofMillis(duration))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement fluentVisibility(By by) {

        int duration = 500;

        FluentWait<AppiumDriver> fluentWait = new FluentWait<>(appiumDriver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(duration))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement fluentVisibility(WebElement element) {

        int duration = 500;

        FluentWait<AppiumDriver> fluentWait = new FluentWait<>(appiumDriver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(duration))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement fluentToBeClickable(By by) {

        int duration = 500;

        FluentWait<AppiumDriver> fluentWait = new FluentWait<>(appiumDriver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(duration))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(ExpectedConditions.elementToBeClickable(by));
    }

    private File getNativeScreenshot(By by) {
        if (customDriver.getDevice() instanceof IOS) {
            IOSDriver iosDriver = (IOSDriver) appiumDriver;
            return iosDriver.findElement(by).getScreenshotAs(OutputType.FILE);
        } else {
            AndroidDriver androidDriver = (AndroidDriver) appiumDriver;
            return androidDriver.findElement(by).getScreenshotAs(OutputType.FILE);
        }
    }

    public void attachScreenToReport(String text, By by) {
        File file = getNativeScreenshot(by);
        try (InputStream is = Files.newInputStream(Path.of(file.getPath()))) {
            Allure.addAttachment(text, is);
        } catch (IOException r) {
            throw new RuntimeException(r);
        }
    }

    public void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

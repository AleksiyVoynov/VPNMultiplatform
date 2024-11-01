package apps.pages.web;

import apps.pages.BasePage;
import driver.CustomDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class SpeedTestPage extends BasePage {

    private final By speedTestButton = By.cssSelector("#knowledge-verticals-internetspeedtest__test_button");
    private final By mbps = By.cssSelector("g-lightbox .spiqle");//mbs data
    private final By other = By.cssSelector("g-lightbox .lAqhed");//other data
    private final By feedBack = By.cssSelector("g-lightbox .W7GCoc.CNbPnc");//mbs data

    public SpeedTestPage(CustomDriver customDriver) {
        super(customDriver);
    }

    @Step("run speed test")
    public SpeedTestResult runSpeedTest() {
        WebElement element = fluentPresenceOfElementLocated(speedTestButton);
        ((JavascriptExecutor) appiumDriver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();

        fluentVisibility(feedBack, Duration.ofMinutes(1));

        List<WebElement> mbps = appiumDriver.findElements(this.mbps);
        List<WebElement> other = appiumDriver.findElements(this.other);

        SpeedTestResult result = new SpeedTestResult(
                mbps.get(0).getText(),
                mbps.get(1).getText(),
                other.get(0).getText(),
                other.get(1).getText());

        String attachmentTest = """
                 Mbps Download: %s
                 Mbps Upload: %s
                 Latency: %s
                 Server: %s
                """.formatted(result.mbpsDownload, result.mbpsUpload, result.latency, result.server);

        //attachScreenToReport("speed result");
        Allure.addAttachment("speed result text", attachmentTest);

        return result;
    }
}

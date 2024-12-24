package apps.multiplatform.pages.web;

import apps.multiplatform.pages.BasePage;
import driver.CustomDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

public class SpeedTestFast extends BasePage {

    private final By mbps = By.cssSelector(".speed-results-container.succeeded");//mbs data
    private final By info = By.cssSelector(".test-info-container");//mbs data
    private final By showMoreButton = By.cssSelector(".more-info-link.localized");//mbs data

    public SpeedTestFast(CustomDriver customDriver) {
        super(customDriver);
    }

    @Step("run speed test")
    public Double runSpeedTest() {
        Double mbps = Double.valueOf(fluentPresenceOfElementLocated(this.mbps, Duration.ofSeconds(30)).getText());
        fluentVisibility(showMoreButton).click();
        String info = fluentPresenceOfElementLocated(this.info).getText();
        String attachmentTest = """
                 Mbps Download: %s
                 Speed test info: %s
                """.formatted(mbps, info);

        //attachScreenToReport("speed result");
        Allure.addAttachment("speed result text", attachmentTest);
        return mbps;
    }
}

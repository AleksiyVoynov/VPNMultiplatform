package apps.multiplatform.pages.ads;

import driver.CustomDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import apps.multiplatform.BasePage;
import org.openqa.selenium.TimeoutException;

import java.util.HashMap;
import java.util.Map;

public class GSMADS extends BasePage {

    public final By adsContainer = By.id("app-interstitial-slot");
    public final By close = By.id("dismiss-button");
    public final By skip =
            AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").index(0)");

    public GSMADS(CustomDriver customDriver) {
        super(customDriver);
    }

    private void switchActivity() {
        Map<String, Object> args = new HashMap<>();
        args.put("appPackage", "com.free.vpn.super.hotspot.open");
        args.put("appActivity", "com.google.android.gms.ads.AdActivity");
        customDriver.getAppiumDriver().executeScript("mobile: startActivity", args);
    }

    private void switchActivityToApp() {
        Map<String, Object> args = new HashMap<>();
        args.put("appPackage", customDriver.getDevice().app.appPackage);
        args.put("appActivity", customDriver.getDevice().app.appActivity);
        customDriver.getAppiumDriver().executeScript("mobile: startActivity", args);
    }

    public void closeADS() {
        switchActivity();
        try {
            fluentVisibility(adsContainer);
            try {
                customDriver.getAppiumDriver().findElement(skip).click();
                customDriver.getAppiumDriver().findElement(close).click();
            } catch (Exception ignored) {
                customDriver.getAppiumDriver().findElement(close).click();
            }
        } catch (TimeoutException ignored) {
        }
        switchActivityToApp();
    }
}

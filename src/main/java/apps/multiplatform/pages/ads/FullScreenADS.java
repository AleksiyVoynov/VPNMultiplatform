package apps.multiplatform.pages.ads;

import apps.multiplatform.BasePage;
import driver.CustomDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.util.HashMap;
import java.util.Map;

public class FullScreenADS extends BasePage {
    public final By adsContainer = By.id("com.free.vpn.super.hotspot.open:id/native_ad_parent");
    public final By close = By.id("com.free.vpn.super.hotspot.open:id/ad_native_close another ads");

    public FullScreenADS(CustomDriver customDriver) {
        super(customDriver);
    }

    private void switchActivity() {
        Map<String, Object> args = new HashMap<>();
        args.put("appPackage", "com.free.vpn.super.hotspot.open");
        args.put("appActivity", "com.free.ads.mix.AdMobNativeFullScreenAdActivity");
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
                customDriver.getAppiumDriver().findElement(close).click();
            } catch (Exception ignored) {
            }
        } catch (TimeoutException ignored) {
        }
        switchActivityToApp();
    }
}

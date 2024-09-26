package pages.apps.vpn.ads;

import driver.CustomDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.BasePage;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class NativeADS extends BasePage {

    public final By close = By.id("dismiss-button");
    public final By skip = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").index(0)");
    //com.free.vpn.super.hotspot.open:id/ad_native_close another ads

    public NativeADS(CustomDriver customDriver) {
        super(customDriver);
    }

    private void switchActivity() {
        Map<String, Object> args = new HashMap<>();
        args.put("appPackage", "com.free.vpn.super.hotspot.open");
        args.put("appActivity", "com.google.android.gms.ads.AdActivity");
        customDriver.getAppiumDriver().executeScript("mobile: startActivity", args);
    }

    public void closeADS() {
        switchActivity();
        fluentVisibility(skip, Duration.ofSeconds(1));
        customDriver.getAppiumDriver().findElement(close);
        switchActivity();
    }
}

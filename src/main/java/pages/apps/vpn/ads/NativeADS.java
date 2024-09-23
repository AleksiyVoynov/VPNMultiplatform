package pages.apps.vpn.ads;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class NativeADS {
    public final By close = By.id("dismiss-button");
    public final By skip = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").index(0)");
}

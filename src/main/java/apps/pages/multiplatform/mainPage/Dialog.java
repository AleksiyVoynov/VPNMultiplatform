package apps.pages.multiplatform.mainPage;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class Dialog {

    public final By dialogTitle = By.id("com.free.vpn.super.hotspot.open:id/tv_dialog_title");
    public final By cancelButton = By.id("com.free.vpn.super.hotspot.open:id/action_cancel_btn");
    public final By reconnectButton = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.free.vpn.super.hotspot.open:id/action_ok_btn\")");
    public final By disconnectButton = By.id("com.free.vpn.super.hotspot.open:id/action_ok_btn");
}

package apps.common;

import com.google.common.collect.ImmutableMap;
import driver.CustomDriver;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;

public class ToggleWiFI {

    private final AppiumDriver appiumDriver;

    public ToggleWiFI(CustomDriver customDriver) {
        this.appiumDriver = customDriver.getAppiumDriver();
    }

    @Step("turn off wifi")
    public void wifiOFF() {
        appiumDriver.executeScript("mobile: shell", ImmutableMap.of("command", "svc wifi disable"));
    }

    @Step("turn on wifi")
    public void wifiON() {
        appiumDriver.executeScript("mobile: shell", ImmutableMap.of("command", "svc wifi enable"));
    }
}

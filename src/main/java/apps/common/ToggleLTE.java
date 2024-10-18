package apps.common;

import com.google.common.collect.ImmutableMap;
import driver.CustomDriver;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;

public class ToggleLTE {

    private final AppiumDriver appiumDriver;

    public ToggleLTE(CustomDriver customDriver) {
        this.appiumDriver = customDriver.getAppiumDriver();
    }

    @Step("turn off lte")
    public void lteOFF() {
        appiumDriver.executeScript("mobile: shell", ImmutableMap.of("command", "svc data disable"));

    }

    @Step("turn on lte")
    public void lteON() {
        appiumDriver.executeScript("mobile: shell", ImmutableMap.of("command", "svc data enable"));
    }
}

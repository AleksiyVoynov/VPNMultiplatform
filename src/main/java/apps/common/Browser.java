package apps.common;

import configs.app.ChromeApp;
import configs.devices.Android;
import configs.devices.IOS;
import driver.CustomDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.time.Duration;

public class Browser {

    private final CustomDriver customDriver;

    public Browser(CustomDriver customDriver) {
        this.customDriver = customDriver;
    }

    @Step("open browser")
    public void openMobileBrowser() {
        if (customDriver.getDevice() instanceof Android) {
            ChromeApp chromeApp = new ChromeApp(customDriver);

            AndroidDriver androidDriver = (AndroidDriver) customDriver.getDriver();
            androidDriver.activateApp(chromeApp.appPackage);

            new Contexts(customDriver).webViewContext("WEBVIEW_chrome", Duration.ofSeconds(5));

        } else if (customDriver.getDevice() instanceof IOS) {


        } else {
            Assert.fail("unknown device platform");
        }
    }

    @Step("close browser")
    public void closeBrowser() {
        if (customDriver.getDevice() instanceof Android) {
            AndroidDriver androidDriver = (AndroidDriver) customDriver.getDriver();

            androidDriver.terminateApp("com.android.chrome");

            androidDriver.activateApp(customDriver.getDevice().app.appPackage);
            new Contexts(customDriver).nativeContext();

        } else if (customDriver.getDevice() instanceof IOS) {


        } else {
            Assert.fail("unknown device platform");
        }
    }

    @Step("get url")
    public void goToURL(String url) {
        customDriver.getAppiumDriver().get(url);
    }
}

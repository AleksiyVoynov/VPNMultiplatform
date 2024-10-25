package configs.platformConfig;


import configs.AppiumConfig;
import configs.devices.Device;
import driver.CustomAndroidDriver;

import java.net.URL;

public class AndroidConfig {

    public CustomAndroidDriver initDriver(Device device) throws Exception {
        AppiumConfig appiumConfig = new AppiumConfig();
        device.capabilities.setCapability("platformName", "Android");
        device.capabilities.setCapability("appium:appPackage", device.app.appPackage);
        device.capabilities.setCapability("appium:appActivity", device.app.appActivity);

        device.capabilities.setCapability("appium:newCommandTimeout", 0);//todo remove it before push anywhere
        device.capabilities.setCapability("appium:autoWebview", false);
        device.capabilities.setCapability("appium:autoWebviewTimeout", 2000);
        device.capabilities.setCapability("appium:noReset", true);


        //device.capabilities.setCapability("appium:ensureWebviewsHavePages", true);
        //device.capabilities.setCapability("appium:enableWebviewDetailsCollection", true);
        //device.capabilities.setCapability("appium:autoWebviewTimeout", 5000);
        //device.capabilities.setCapability("appium:systemPort", appiumConfig.androidWDPort);
        //device.capabilities.setCapability("appium:chromedriverPort", appiumConfig.androidChromePort);
        return new CustomAndroidDriver(
                new URL("http://" + appiumConfig.appiumAndroidIP + ":" + appiumConfig.appiumAndroidPort + "/"),
                device);
    }
}
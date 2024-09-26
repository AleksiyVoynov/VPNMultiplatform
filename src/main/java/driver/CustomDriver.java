package driver;

import configs.devices.Device;
import io.appium.java_client.AppiumDriver;


public interface CustomDriver {

    AppiumDriver getAppiumDriver();

    Object getDriver();

    Device getDevice();
}

package driver;

import configs.devices.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;

public class CustomAndroidDriver extends AndroidDriver implements CustomDriver {

    public Device device;

    public CustomAndroidDriver(URL remoteAddress, Device device) {
        super(remoteAddress, device.capabilities);
        this.device = device;
    }

    @Override
    public AppiumDriver getAppiumDriver() {
        return this;
    }

    @Override
    public AndroidDriver getDriver() {
        return this;
    }

    @Override
    public Device getDevice() {
        return this.device;
    }
}

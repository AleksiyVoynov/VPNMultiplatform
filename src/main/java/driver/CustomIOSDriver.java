package driver;

import configs.devices.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;

public class CustomIOSDriver extends IOSDriver implements CustomDriver {

    public Device device;

    public CustomIOSDriver(URL remoteAddress, Device device) {
        super(remoteAddress, device.capabilities);
        this.device = device;
    }

    @Override
    public AppiumDriver getAppiumDriver() {
        return this;
    }

    @Override
    public IOSDriver getDriver() {
        return this;
    }

    @Override
    public Device getDevice() {
        return this.device;
    }
}

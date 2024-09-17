package configs;

import configs.devices.Device;
import io.appium.java_client.AppiumDriver;

public class Config {
    public Device device;

    public Config(Device device) {
        this.device = device;
    }
}
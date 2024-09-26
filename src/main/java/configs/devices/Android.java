package configs.devices;

import configs.app.App;

public class Android extends Device {

    public Android(String deviceName, String model, String version, String uDID, App app) {
        this.app = app;
        this.os = "android";
        this.origin = "simulator";
        this.device = deviceName;

        this.model = model;
        this.name =  this.device + " " + model;
        this.platformVersion = version;
        this.uDID = uDID;

        this.capabilities.setCapability("appium:automationName", "UiAutomator2");
        this.capabilities.setCapability("appium:platformVersion", platformVersion);
        this.capabilities.setCapability("appium:udid", uDID);
        this.capabilities.setCapability("appium:deviceName", name);
    }
}

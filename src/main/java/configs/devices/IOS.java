package configs.devices;

import configs.app.App;

public class IOS extends Device {

    public IOS(String deviceName, String model, String version, String uDID, App app, String xcodeOrgId) {
        this.app = app;
        this.os = "ios";
        this.origin = "simulator";
        this.device = deviceName;
        this.model = model;
        this.version = version;
        this.uDID = uDID;

        this.capabilities.setCapability("appium:platformVersion", version);
        this.capabilities.setCapability("appium:udid", uDID);
        this.capabilities.setCapability("appium:deviceName", name);
        this.capabilities.setCapability("appium:xcodeSigningId", "iPhone Developer");
        this.capabilities.setCapability("appium:forceSimulatorSoftwareKeyboardPresence", true);
        this.capabilities.setCapability("appium:xcodeOrgId", xcodeOrgId);
    }
}

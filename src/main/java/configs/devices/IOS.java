package configs.devices;

import configs.app.App;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IOS extends Device {

    public IOS(String deviceName, String model, String version, String uDID, App app, String xcodeOrgId) {
        this.app = app;
        this.os = "ios";
        this.origin = "simulator";
        this.device = deviceName;
        this.model = model;
        this.platformVersion = version;
        this.uDID = uDID;

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("appium:platformVersion", platformVersion);
        cap.setCapability("appium:udid", uDID);
        cap.setCapability("appium:deviceName", name);
        cap.setCapability("appium:xcodeSigningId", "iPhone Developer");
        cap.setCapability("appium:forceSimulatorSoftwareKeyboardPresence", true);
        cap.setCapability("appium:xcodeOrgId", xcodeOrgId);
        this.capabilities = cap;
    }
}

package common;

import com.google.common.collect.ImmutableMap;
import configs.Config;
import configs.devices.Android;
import configs.devices.IOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.remote.DriverCommand;

import java.util.ArrayList;
import java.util.List;

public class Contexts {

    private Config config;
    private AppiumDriver appiumDriver;
    private String webViewContext;

    public Contexts(Config config, AppiumDriver appiumDriver) {
        this.config = config;
        this.appiumDriver = appiumDriver;
        this.webViewContext = config.device.app.webView;
    }

    public Contexts(Config config, AppiumDriver appiumDriver, String webView) {
        this.config = config;
        this.appiumDriver = appiumDriver;
        this.webViewContext = webView;
    }

    public void webViewContext() {
        if (config.device instanceof Android) {
            ((SupportsContextSwitching) appiumDriver).context(webViewContext);
        } else if (config.device instanceof IOS) {
            ((SupportsContextSwitching) appiumDriver).context(webViewContext);
        }
    }
    public List<String> getContext() {
        return new ArrayList<>(((SupportsContextSwitching) appiumDriver).getContextHandles());
    }

    public void nativeContext() {
        if (config.device instanceof Android) {
            appiumDriver.execute(DriverCommand.SWITCH_TO_CONTEXT, ImmutableMap.of("name", "NATIVE_APP"));
        } else if (config.device instanceof IOS){
            ((SupportsContextSwitching) appiumDriver).context("NATIVE_APP");
        }
    }
}

package common;

import com.google.common.collect.ImmutableMap;
import configs.devices.Android;
import configs.devices.IOS;
import driver.CustomDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.remote.DriverCommand;

import java.util.ArrayList;
import java.util.List;

public class Contexts {

    private final CustomDriver customDriver;
    private final String webViewContext;

    public Contexts(CustomDriver customDriver) {
        this.customDriver = customDriver;
        this.webViewContext = customDriver.getDevice().app.webView;
    }

    public Contexts(CustomDriver customDriver, String webView) {
        this.customDriver = customDriver;
        this.webViewContext = webView;
    }

    public void webViewContext() {
        if (customDriver.getDevice() instanceof Android) {
            ((SupportsContextSwitching) customDriver).context(webViewContext);
        } else if (customDriver.getDevice() instanceof IOS) {
            ((SupportsContextSwitching) customDriver).context(webViewContext);
        }
    }
    public List<String> getContext() {
        return new ArrayList<>(((SupportsContextSwitching) customDriver).getContextHandles());
    }

    public void nativeContext() {
        if (customDriver.getDevice() instanceof Android) {
            customDriver.getAppiumDriver().execute(DriverCommand.SWITCH_TO_CONTEXT, ImmutableMap.of("name", "NATIVE_APP"));
        } else if (customDriver.getDevice() instanceof IOS){
            ((SupportsContextSwitching) customDriver).context("NATIVE_APP");
        }
    }
}

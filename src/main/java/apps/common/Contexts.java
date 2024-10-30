package apps.common;

import com.google.common.collect.ImmutableMap;
import configs.app.App;
import configs.app.ChromeApp;
import configs.devices.Android;
import configs.devices.IOS;
import driver.CustomDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.remote.DriverCommand;
import org.testng.Assert;

import java.time.Duration;
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

    public void webViewContext(Duration duration) {
        if (customDriver.getDevice() instanceof Android) {
            waitContext(webViewContext, duration);
        } else if (customDriver.getDevice() instanceof IOS) {
            ((SupportsContextSwitching) customDriver).context(webViewContext);
        }
    }

    public void webViewContext(String context, Duration duration) {
        if (customDriver.getDevice() instanceof Android) {
            waitContext(context, duration);
        } else if (customDriver.getDevice() instanceof IOS) {
            Assert.fail("add realisation for ios");
        }
    }

    public void webViewContext(String webViewContext) {
        ((SupportsContextSwitching) customDriver).context(webViewContext);
    }

    public List<String> getContext() {
        return new ArrayList<>(((SupportsContextSwitching) customDriver).getContextHandles());
    }

    public void nativeContext() {
        if (customDriver.getDevice() instanceof Android) {
            customDriver.getAppiumDriver().execute(DriverCommand.SWITCH_TO_CONTEXT, ImmutableMap.of("name", "NATIVE_APP"));
        } else if (customDriver.getDevice() instanceof IOS) {
            ((SupportsContextSwitching) customDriver).context("NATIVE_APP");
        }
    }

    private void waitContext(String context, Duration duration) {
        long endTime = System.currentTimeMillis() + duration.toMillis();
        while (System.currentTimeMillis() < endTime) {
            try {
                ((SupportsContextSwitching) customDriver).context(context);
                return;
            } catch (io.appium.java_client.NoSuchContextException e) {
                try {
                    Thread.sleep(350);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
        throw new RuntimeException("Context '" + context + "' not found within the specified time.");
    }

}

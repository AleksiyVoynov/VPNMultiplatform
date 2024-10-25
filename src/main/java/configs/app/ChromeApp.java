package configs.app;

import driver.CustomDriver;
import io.appium.java_client.remote.SupportsContextSwitching;

import java.util.ArrayList;

public class ChromeApp extends App {
    public String webViewContext;

    public ChromeApp() {
        this.name = "Chrome";
        this.appPackage = "com.android.chrome";
        this.appActivity = "com.google.android.apps.chrome.Main";
    }

    public ChromeApp(CustomDriver customDriver) {
        this.name = "Chrome";
        this.appPackage = "com.android.chrome";
        this.appActivity = "com.google.android.apps.chrome.Main";

        ArrayList<String> list = new ArrayList<> (((SupportsContextSwitching) customDriver)
                .getContextHandles());

        this.webViewContext = list.stream()
                .filter(s -> s.contains("chrome"))
                .findFirst()
                .orElse(list.get(list.size() - 1));
    }
}

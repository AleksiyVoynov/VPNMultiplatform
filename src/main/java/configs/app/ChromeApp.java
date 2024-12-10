package configs.app;

import driver.CustomDriver;

public class ChromeApp extends App {

    public ChromeApp() {
        this.name = "Chrome";
        this.appPackage = "com.android.chrome";
        this.appActivity = "com.google.android.apps.chrome.Main";
    }
}

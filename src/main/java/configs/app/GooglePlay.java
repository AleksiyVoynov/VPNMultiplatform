package configs.app;

import driver.CustomDriver;

public class GooglePlay extends App {

    public GooglePlay() {
        this.name = "Google Play";
        this.appPackage = "com.android.vending";
        this.appActivity = "com.google.android.finsky.activities.MainActivity";
        this.appActivity = "com.android.vending.AssetBrowserActivity";
    }
}

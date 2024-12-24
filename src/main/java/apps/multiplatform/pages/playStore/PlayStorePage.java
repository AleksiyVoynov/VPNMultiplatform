package apps.multiplatform.pages.playStore;

import apps.common.Contexts;
import apps.multiplatform.pages.BasePage;
import configs.app.GooglePlay;
import driver.CustomDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

public class PlayStorePage extends BasePage {
    public PlayStorePage(CustomDriver customDriver) {
        super(customDriver);
    }

    @Step("open google play")
    public void openGooglePlay() {
        GooglePlay googlePlay = new GooglePlay();
        AndroidDriver androidDriver = (AndroidDriver) customDriver.getDriver();
        androidDriver.activateApp(googlePlay.appPackage);
    }

    @Step("close google play")
    public void closeGooglePlay() {
        GooglePlay googlePlay = new GooglePlay();
        AndroidDriver androidDriver = (AndroidDriver) customDriver.getDriver();

        androidDriver.terminateApp(googlePlay.appPackage);

        androidDriver.activateApp(customDriver.getDevice().app.appPackage);
        new Contexts(customDriver).nativeContext();
    }
}

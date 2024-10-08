package apps.common;

import configs.devices.Android;
import driver.CustomDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.Assert;

import java.util.Objects;

public class AdsListener implements WebDriverListener {

    private final CustomDriver customDriver;

    public AdsListener(CustomDriver customDriver) {
        this.customDriver = customDriver;
    }

    // Перехват метода findElement() перед его выполнением
    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        closeAdIfPresent();  // Проверяем наличие рекламы перед поиском элемента
    }

    // Метод для проверки и закрытия рекламы
    private void closeAdIfPresent() {
        if (customDriver.getDevice() instanceof Android) {

            String fullScreenActivity = "com.free.ads.mix.AdMobNativeFullScreenAdActivity";

            if (Objects.equals(((AndroidDriver) customDriver.getDriver()).currentActivity(), fullScreenActivity)) {
                ((AndroidDriver) customDriver.getDriver()).navigate().back();
            } else {

                String gsmAdActivity = "com.google.android.gms.ads.AdActivity";

                if (Objects.equals(((AndroidDriver) customDriver.getDriver()).currentActivity(), gsmAdActivity)) {
                    ((AndroidDriver) customDriver.getDriver()).navigate().back();
                    if (Objects.equals(((AndroidDriver) customDriver.getDriver()).currentActivity(), gsmAdActivity)) {
                        ((AndroidDriver) customDriver.getDriver()).navigate().back();
                    }
                }
            }
        } else {
            Assert.fail("unknown device platform");
        }
    }
}

package apps.vpn.pages.banners;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SignInBanner extends Banner {

    Boolean icon;
    String title;
    String text;
    Boolean arrowIcon;


    public SignInBanner(AppiumDriver appiumDriver) {
        banner = By.id("com.free.vpn.super.hotspot.open:id/menu_sign_in_button");

        icon = appiumDriver.findElement(banner)
                .findElement(By.id("com.free.vpn.super.hotspot.open:id/menuIvKey")).isDisplayed();

        title = appiumDriver.findElement(banner)
                .findElement(By.id("com.free.vpn.super.hotspot.open:id/menuTvSignIn")).getText();

        text = appiumDriver.findElement(banner)
                .findElement(By.id("com.free.vpn.super.hotspot.open:id/tv_already_vip")).getText();

        arrowIcon = appiumDriver.findElement(banner)
                .findElement(By.id("com.free.vpn.super.hotspot.open:id/iv_sign_in_arrow")).isDisplayed();


    }
}

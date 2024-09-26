package apps.vpn.pages.banners;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.util.Objects;

public class ReNewNowBanner extends Banner {

    String title;
    String text_1;
    String text_2;
    Boolean icon;
    public ReNewNowBanner(AppiumDriver appiumDriver) {
         banner = By.id("com.free.vpn.super.hotspot.open:id/view_stub_banner");

         title = appiumDriver.findElement(banner)
                 .findElement(By.id("com.free.vpn.super.hotspot.open:id/tv_sub_banner")).getText();

         text_1 = appiumDriver.findElement(banner)
                 .findElement(By.id("com.free.vpn.super.hotspot.open:id/tv_no_ads")).getText();

         text_2 = appiumDriver.findElement(banner)
                 .findElement(By.id("com.free.vpn.super.hotspot.open:id/tv_connect_ten_devices")).getText();

         icon = appiumDriver.findElement(banner)
                 .findElement(By.id("com.free.vpn.super.hotspot.open:id/iv_free_user_buy_icon")).isDisplayed();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReNewNowBanner that = (ReNewNowBanner) o;
        return Objects.equals(title, that.title)
                && Objects.equals(text_1, that.text_1)
                && Objects.equals(text_2, that.text_2)
                && Objects.equals(icon, that.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text_1, text_2, icon);
    }

    @Override
    public String toString() {
        return "ReNewNowBanner{" +
                "title='" + title + '\'' +
                ", text_1='" + text_1 + '\'' +
                ", text_2='" + text_2 + '\'' +
                ", icon=" + icon +
                '}';
    }
}

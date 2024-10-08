package apps.multiplatform.pages.connection;

import apps.multiplatform.BasePage;
import apps.multiplatform.pages.mainPage.MainScreen;
import driver.CustomDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public abstract class Connection extends BasePage {

    public final By backButton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageButton\")");

    public final By youAreConnectedText = By.id("com.free.vpn.super.hotspot.open:id/tv_connect_status");
    public final By timer = By.id("com.free.vpn.super.hotspot.open:id/tv_timer");

    //vpn server block
    public final By countryFlag = By.id("com.free.vpn.super.hotspot.open:id/iv_country_flag");
    public final By vpnServer = By.id("com.free.vpn.super.hotspot.open:id/tv_vpn_server");
    public final By titleVpnIp = By.id("com.free.vpn.super.hotspot.open:id/tv_title_vpn_ip");
    public final By ip = By.id("com.free.vpn.super.hotspot.open:id/tv_vpn_ip");
    public final By titleCity = By.id("com.free.vpn.super.hotspot.open:id/tv_title_city");
    public final By city = By.id("com.free.vpn.super.hotspot.open:id/tv_city");
    public final By titleCountry = By.id("com.free.vpn.super.hotspot.open:id/tv_title_country");
    public final By country = By.id("com.free.vpn.super.hotspot.open:id/tv_country");

    public Connection(CustomDriver customDriver) {
        super(customDriver);
    }

    @Step("tap back")
    public MainScreen tapBack() {
        fluentVisibility(backButton).click();
        return new MainScreen(customDriver);
    }
}

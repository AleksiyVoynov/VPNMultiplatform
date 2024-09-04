package pages.vpn;

import configs.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.BasePage;

public class MainScreen extends BasePage {
    public final By connectButton = By.id("com.free.vpn.super.hotspot.open:id/iv_connect");

    public MainScreen(Config config) {
        super(config);
    }

    @Step("make search {0}")
    public void searchFor() {
    }
}

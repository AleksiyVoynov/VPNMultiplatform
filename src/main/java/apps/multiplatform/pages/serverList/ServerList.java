package apps.multiplatform.pages.serverList;

import apps.common.FingerMove;
import apps.multiplatform.BasePage;
import apps.multiplatform.pages.connection.ConnectionDetail;
import driver.CustomDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServerList extends BasePage {

    public final By backButton = AppiumBy.androidUIAutomator("new UiSelector().description(\"Navigate up\")");

    public final By changeLocationText = AppiumBy.androidUIAutomator("new UiSelector().text(\"Change Location\")");

    public final By refreshButton = By.id("com.free.vpn.super.hotspot.open:id/refresh");

    public final By freeButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"Free\")");
    public final By vipButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"Free\")");
    public final By historyButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"History\")");

    public final By fastenLocationButton = By.id("com.free.vpn.super.hotspot.open:id/item_fastest_country_name");

    public final By qualityServerText = AppiumBy.androidUIAutomator("new UiSelector().text(\"Quality Server\")");

    public ServerList(CustomDriver customDriver) {
        super(customDriver);
    }

    @Step("tap free")
    public ServerList tapFree() {
        fluentVisibility(freeButton).click();
        return this;
    }

    @Step("tap fastest location")
    public ConnectionDetail tapFastestLocation() {
        fluentVisibility(fastenLocationButton).click();
        return new ConnectionDetail(customDriver);
    }

    @Step("tap on server")
    public ConnectionDetail tapServer(String serverName) {
        //todo to be continue
        return new ConnectionDetail(customDriver);
    }

    @Step("open cluster")
    public ServerList openCluster(String cluster) {
        //todo to be continue
        return this;
    }

    @Step("parsing servers")
    public List<Server> serversParsing() {
        UIServerParser UIServerParser = new UIServerParser(customDriver);
        UIServerParser.parseServersWithoutSwipes();
        return UIServerParser.servers;
    }
}

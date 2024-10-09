package apps.multiplatform.pages.serverList;

import apps.common.FingerMove;
import apps.multiplatform.BasePage;
import apps.multiplatform.pages.connection.ConnectionDetail;
import driver.CustomDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ServerList extends BasePage {

    public final By backButton =
            AppiumBy.androidUIAutomator("new UiSelector().description(\"Navigate up\")");

    public final By changeLocationText =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Change Location\")");

    public final By refreshButton =
            By.id("com.free.vpn.super.hotspot.open:id/refresh");

    public final By freeButton =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Free\")");
    public final By vipButton =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Free\")");
    public final By historyButton =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"History\")");

    public final By fastenLocationButton =
            By.id("com.free.vpn.super.hotspot.open:id/item_fastest_country_name");

    public final By qualityServerText =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Quality Server\")");

    private final By listID =
            AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.free.vpn.super.hotspot.open:id/lv_server_list\")");
    private final By serverGroups =
            AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.LinearLayout\")");
    private final By serverName =
            AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.free.vpn.super.hotspot.open:id/item_country_name\")");
    private final By button =
            AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.free.vpn.super.hotspot.open:id/item_radio_button\")");

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
        var names = appiumDriver
                .findElement(this.listID)
                .findElements(this.serverName);

        for (var element : names) {
            var text = element.getText();
            if (!(text.contains("(") || text.contains(")"))) {
                if (text.equals(serverName)) {
                    element.click();
                    return new ConnectionDetail(customDriver);
                }
            }
        }
        Assert.fail("can't find server with name " + serverName);
        return new ConnectionDetail(customDriver);
    }

    @Step("open cluster")
    public ServerList openCluster(String cluster) throws InterruptedException {
        findCluster(cluster).findElement(button).click();
        return this;
    }

    @Step("parsing servers")
    public List<Server> serversParsing() {
        List<Server> servers = new ArrayList<>();

        String currentName = "";
        int index = 0;

        //find groups
        WebElement listID = appiumDriver.findElement(this.listID);
        List<WebElement> serverGroups = listID.findElements(this.serverGroups);

        //get group
        WebElement currentGroup = serverGroups.get(0);
        String serverName = currentGroup.findElement(this.serverName).getText();

        new FingerMove(appiumDriver).doSwipe(0.5, 0.8, 0.5, 0.66);

        while (checkHasMoreGroups(serverName)) {

            if (!currentName.isEmpty()) {
                //find groups
                listID = appiumDriver.findElement(this.listID);
                serverGroups = listID.findElements(this.serverGroups);

                //get group
                currentGroup = serverGroups.get(index);
                serverName = currentGroup.findElement(this.serverName).getText();
            }

            //open list
            currentGroup.findElement(this.button).click();

            //pars opened list
            servers.addAll(parsServers(serverName));

            //close list
            closeList(serverName);


            currentName = serverName;
            index = getNextIndex(serverName);
            if (index == -1) {
                break;
            }
        }
        return servers;
    }

    private List<Server> parsServers(String serverName) {
        List<Server> servers = new ArrayList<>();
        var names = appiumDriver
                .findElement(this.listID)
                .findElements(this.serverName);

        for (var element : names) {
            var text = element.getText();
            if (!(text.contains("(") || text.contains(")"))) {
                servers.add(new Server(serverName, text));
            }
        }
        return servers;
    }


    private boolean checkHasMoreGroups(String currentCluster) {
        List<WebElement> serverGroups = appiumDriver
                .findElement(this.listID)
                .findElements(this.serverGroups);

        return !serverGroups.get(serverGroups.size() - 1).getText().equals(currentCluster);
    }

    private int getNextIndex(String serverName) {
        var serverGroups = appiumDriver
                .findElement(this.listID)
                .findElements(this.serverGroups);

        for (int i = 0; i < serverGroups.size(); i++) {
            var name = serverGroups.get(i).findElement(this.serverName).getText();
            if (name.equals(serverName)) {
                return (i < serverGroups.size() - 1) ? i + 1 : -1;
            }
        }
        return -1;
    }


    private void closeList(String serverName) {
        var groups = appiumDriver
                .findElement(this.listID)
                .findElements(this.serverGroups);

        for (var group : groups) {
            String text;
            try {
                text = group.findElement(this.serverName).getText();
            } catch (org.openqa.selenium.NoSuchElementException e) {
                text = "";
            }

            if (text.equals(serverName)) {
                group.findElement(this.button).click();
                break;
            }
        }
    }


    private WebElement findCluster(String cluster) throws InterruptedException {
        new FingerMove(appiumDriver).doSwipe(0.5, 0.8, 0.5, 0.66);

        //find groups
        WebElement listID = appiumDriver.findElement(this.listID);
        List<WebElement> serverGroups = listID.findElements(this.serverGroups);

        //get group
        WebElement currentGroup = serverGroups.get(0);
        String serverName = currentGroup.findElement(this.serverName).getText();

        while (checkHasMoreGroups(serverName)) {
            //find groups
            serverGroups = appiumDriver.findElement(this.listID).findElements(this.serverGroups);

            for (WebElement serverGroup : serverGroups) {
                String s;
                try {
                    s = serverGroup.findElement(this.serverName).getText();
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    new FingerMove(customDriver.getAppiumDriver()).doSwipe(0.5, 0.8, 0.5, 0.75);
                    continue;
                }
                if (s.equals(cluster)) {
                    return serverGroup;
                }
            }
            new FingerMove(customDriver.getAppiumDriver()).doSwipe(0.5, 0.8, 0.5, 0.4);
        }
        Assert.fail("can't find cluster with name " + cluster);
        return null;
    }
}

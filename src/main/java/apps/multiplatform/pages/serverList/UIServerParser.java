package apps.multiplatform.pages.serverList;


import apps.common.FingerMove;
import apps.multiplatform.BasePage;
import driver.CustomDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

public class UIServerParser extends BasePage {

    List<Server> servers = new ArrayList<>();

    private final AppiumDriver appiumDriver;

    private final By listID = By.id("com.free.vpn.super.hotspot.open:id/lv_server_list");
    private final By serverGroups = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.LinearLayout\")");
    private final By serverName = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.free.vpn.super.hotspot.open:id/item_country_name\")");
    private final By button = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.free.vpn.super.hotspot.open:id/item_radio_button\")");

    public UIServerParser(CustomDriver customDriver) {
        super(customDriver);
        this.appiumDriver = customDriver.getAppiumDriver();
    }

    void parseServersWithoutSwipes() {
        String currentName = "";
        int index = 0;

        //find groups
        WebElement listID = appiumDriver.findElement(this.listID);
        List<WebElement> serverGroups = listID.findElements(this.serverGroups);

        //get group
        WebElement currentGroup = serverGroups.get(6);
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
            parsServers(serverName);

            //close list
            closeList(serverName);


            currentName = serverName;
            index = getNextIndex(serverName);
            if (index == -1) {
                break;
            }
        }
    }

    private void parsServers(String serverName) {
        var names = appiumDriver
                .findElement(this.listID)
                .findElements(this.serverName);

        for (var element : names) {
            var text = element.getText();
            if (!(text.contains("(") || text.contains(")"))) {
                servers.add(new Server(serverName, text));
            }
        }
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
}

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

    private final AppiumDriver appiumDriver;

    private final By listID = By.id("com.free.vpn.super.hotspot.open:id/lv_server_list");
    private final By serverGroups = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.LinearLayout\")");
    private final By serverName = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.free.vpn.super.hotspot.open:id/item_country_name\")");
    private final By button = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.free.vpn.super.hotspot.open:id/item_radio_button\")");

    public Map<String, List<String>> servers = new HashMap<>();

    public UIServerParser(CustomDriver customDriver) {
        super(customDriver);
        this.appiumDriver = customDriver.getAppiumDriver();
    }

    public void parseServersWithoutSwipes() {
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

            //add group to map
            servers.put(serverName, new ArrayList<>());

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
        WebElement listID = appiumDriver.findElement(this.listID);
        List<WebElement> names = listID.findElements(this.serverName);

        for (WebElement element : names) {
            String text = element.getText();
            if (!text.contains("(") && !text.contains(")")) {
                servers.get(serverName).add(text);
            }
        }
    }

    private boolean checkHasMoreGroups(String currentCluster) {
        WebElement listID = appiumDriver.findElement(this.listID);
        List<WebElement> serverGroups = listID.findElements(this.serverGroups);
        return !serverGroups.get(serverGroups.size() - 1).getText().equals(currentCluster);
    }

    private int getNextIndex(String serverName) {
        List<WebElement> serverGroups = appiumDriver.findElement(this.listID).findElements(this.serverGroups);

        int size = serverGroups.size();

        for (int i = 0; i < size; i++) {
            String name = serverGroups.get(i).findElement(this.serverName).getText();
            if (name.equals(serverName)) {
                if (i < size - 1) {
                    return i + 1;
                } else {
                    return -1;
                }
            }
        }
        return -1;
    }

    private void closeList(String serverName) {
        WebElement listID = appiumDriver.findElement(this.listID);
        List<WebElement> groups = listID.findElements(this.serverGroups);

        for (int i = 0; i < groups.size(); i++) {
            String text;

            if (i == 0) {
                try {
                    text = groups.get(i).findElement(this.serverName).getText();
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    text = "";
                }
            } else {
                text = groups.get(i).findElement(this.serverName).getText();
            }

            if (text.equals(serverName)) {
                groups.get(i).findElement(this.button).click();
                break;
            }
        }
    }
}

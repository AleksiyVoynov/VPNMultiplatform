package apps.multiplatform.pages.serverList;

import apps.common.FingerMove;
import apps.multiplatform.BasePage;
import apps.multiplatform.pages.connection.ConnectionDetail;
import driver.CustomDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;
import java.util.*;

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
        pause(Duration.ofMillis(500));
        fluentVisibility(freeButton).click();
        return this;
    }

    @Step("tap fastest location")
    public ConnectionDetail tapFastestLocation() {
        fluentVisibility(fastenLocationButton).click();
        return new ConnectionDetail(customDriver);
    }

    @Step("tap on server")
    public ConnectionDetail tapServer(Server server) {
        var names = appiumDriver
                .findElement(this.listID)
                .findElements(this.serverName);

        for (var element : names) {
            var text = element.getText();
            if (!(text.contains("(") || text.contains(")"))) {
                if (text.equals(server.name)) {
                    element.click();
                    return new ConnectionDetail(customDriver);
                }
            }
        }
        Assert.fail("can't find server with name " + server.name);
        return new ConnectionDetail(customDriver);
    }

    @Step("open cluster")
    public ServerList openCluster(Server server, int clusters) {
        if (server.clusterIndex > clusters / 2) {
            scrollToEndOfList();
            Objects.requireNonNull(scrollUp(server, clusters)).findElement(button).click();
        } else {
            Objects.requireNonNull(scrollDown(server)).findElement(button).click();
        }
        return this;
    }

    @Step("open cluster")
    public ServerList openCluster(Server server) {
        Objects.requireNonNull(scrollDown(server)).findElement(button).click();
        return this;
    }

    @Step("parsing servers")
    public List<Server> serversParsing() {
        List<Server> servers = new ArrayList<>();

        String currentName = "";
        int index = 0;
        int indexNumber = 0;

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
            servers.addAll(parsServers(indexNumber, serverName));
            indexNumber++;

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

    private List<Server> parsServers(int indexNumber, String serverName) {
        List<Server> servers = new ArrayList<>();
        var names = appiumDriver
                .findElement(this.listID)
                .findElements(this.serverName);

        for (var element : names) {
            var text = element.getText();
            if (!(text.contains("(") || text.contains(")"))) {
                servers.add(new Server(indexNumber, serverName, text));
            }
        }
        return servers;
    }

    private String getLastGroup() {
        List<WebElement> serverGroups = appiumDriver
                .findElement(this.listID)
                .findElements(this.serverGroups);
        return serverGroups.get(serverGroups.size() - 1).getText();
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
            } catch (NoSuchElementException e) {
                text = "";
            }

            if (text.equals(serverName)) {
                group.findElement(this.button).click();
                break;
            }
        }
    }


    private WebElement scrollDown(Server server) {
        new FingerMove(appiumDriver).doSwipe(0.5, 0.8, 0.5, 0.66);

        //find groups
        WebElement listID = appiumDriver.findElement(this.listID);
        List<WebElement> serverGroups = listID.findElements(this.serverGroups);

        //scroll to needed element +-
        for (int i = 0; i < server.clusterIndex / (serverGroups.size() - 1); i++) {
            new FingerMove(appiumDriver)
                    .doSwipe(0.5, 0.8, 0.5, 0.31);
            pause(Duration.ofMillis(500));
        }

        //get group
        WebElement currentGroup = serverGroups.get(0);
        String serverName = currentGroup.findElement(this.serverName).getText();

        int index;
        String currentGroupName = "";

        while (checkHasMoreGroups(serverName)) {
            //find groups
            serverGroups = appiumDriver.findElement(this.listID).findElements(this.serverGroups);



            for (WebElement serverGroup : serverGroups) {
                try {
                    currentGroupName = serverGroup.findElement(this.serverName).getText();
                } catch (NoSuchElementException e) {
                    new FingerMove(appiumDriver)
                            .doSwipe(0.5, 0.8, 0.5, 0.75);
                    continue;
                }
                if (currentGroupName.equals(server.cluster)) {
                    return serverGroup;
                }
            }
            new FingerMove(appiumDriver)
                    .doSwipe(0.5, 0.8, 0.5, 0.5);

            index = getNextIndex(currentGroupName);
            if (index == -1) {
                break;
            }
        }
        Assert.fail("can't find cluster with name " + server.cluster);
        return null;
    }

    private WebElement scrollUp(Server server, int clusters) {

        //find groups
        WebElement listID = appiumDriver.findElement(this.listID);
        List<WebElement> serverGroups = listID.findElements(this.serverGroups);
        Collections.reverse(serverGroups);

        for (WebElement e : serverGroups) {
            String clusterName = e.findElement(this.serverName).getText();
            if (clusterName.equals(server.cluster)) {
                return e;
            }
        }
        //scroll to needed element +-
        for (int i = 0; i < (clusters - server.clusterIndex) / serverGroups.size() - 1; i++) {
            new FingerMove(appiumDriver)
                    .doSwipe(0.5, 0.31, 0.5, 0.8);
            pause(Duration.ofMillis(500));
        }

        String actuaName = "";

        for (int i = 0; i < 3; i++) {
            //find groups
            serverGroups = appiumDriver.findElement(this.listID).findElements(this.serverGroups);
            Collections.reverse(serverGroups);

            for (WebElement serverGroup : serverGroups) {
                try {
                    actuaName = serverGroup.findElement(this.serverName).getText();
                } catch (NoSuchElementException e) {
                    new FingerMove(appiumDriver)
                            .doSwipe(0.5, 0.75, 0.5, 0.8);
                    continue;
                }
                if (actuaName.equals(server.cluster)) {
                    return serverGroup;
                }
            }
            new FingerMove(appiumDriver)
                    .doSwipe(0.5, 0.5, 0.5, 0.8);
        }

        Assert.fail("can't find cluster with name " + server.cluster);
        return null;
    }


    private void scrollToEndOfList() {
        String previousLastName = "";
        boolean isEnd = false;

        while (!isEnd) {
            List<WebElement> elements = appiumDriver.findElement(this.listID).findElements(this.serverGroups);

            String currentLastName;
            try {
                currentLastName = elements.get(elements.size() - 1).findElement(this.serverName).getText();
            } catch (NoSuchElementException e) {
                new FingerMove(appiumDriver)
                        .doSwipe(0.5, 0.8, 0.5, 0.75);
                continue;
            }

            if (!previousLastName.equals(currentLastName)) {
                previousLastName = currentLastName;
                new FingerMove(appiumDriver)
                        .doQuickSwipe(0.5, 0.85, 0.5, 0.15);
            } else {
                isEnd = true;
            }
        }
    }

    public List<String> getAllClusters() {
        List<String> serverNames = new ArrayList<>();
        Set<String> seenServers = new HashSet<>();
        boolean isEndOfList = false;

        while (!isEndOfList) {
            List<WebElement> serverElements = appiumDriver.findElement(this.listID).findElements(this.serverName);

            List<String> currentScreenServers = new ArrayList<>();
            for (WebElement element : serverElements) {
                String serverName = element.getText();
                currentScreenServers.add(serverName);
                if (!seenServers.contains(serverName)) {
                    serverNames.add(serverName);
                    seenServers.add(serverName);
                }
            }

            new FingerMove(appiumDriver).doSwipe(0.5, 0.8, 0.5, 0.31);

            List<WebElement> newServerElements = appiumDriver.findElement(this.listID).findElements(this.serverName);

            List<String> afterSwipeScreenServers = new ArrayList<>();
            for (WebElement element : newServerElements) {
                afterSwipeScreenServers.add(element.getText());
            }

            if (currentScreenServers.equals(afterSwipeScreenServers)) {
                isEndOfList = true;
            }
        }

        return serverNames;
    }
}

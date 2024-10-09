package apps.multiplatform.temp;

import apps.BaseTest;
import apps.multiplatform.pages.mainPage.MainScreen;
import apps.multiplatform.pages.serverList.Server;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class TempTest extends BaseTest {
    private List<String> serverList;

    @BeforeClass
    public void generateServers() {
        serverList = new ArrayList<>();
        serverList.add("German 32");
    }

    @DataProvider(name = "serverData")
    public Object[][] serverData() {
        Object[][] data = new Object[serverList.size()][1];
        for (int i = 0; i < serverList.size(); i++) {
            data[i][0] = serverList.get(i);
        }
        return data;
    }

    @Test(priority = 1, description = "check connection IKEv2", dataProvider = "serverData")
    @Severity(SeverityLevel.CRITICAL)
    @Description("""
            Test Description:
            This test checks the availability of servers for the IKEv2 protocol.
            The check is performed on each server in the list for free user""")
    public void Temp1(String server) {
        List<Server> servers = new MainScreen(customDriver)
                .tapIKEv2()
                .tapFastestLocation()
                .tapFree()
                .serversParsing();

        System.out.println("Servers Quantity: " + servers.size());
        servers.forEach(System.out::println);
    }


/*    public void validationElements() {
        MainScreen mainScreen = new MainScreen(customDriver);
        try {
            customDriver.getAppiumDriver().findElement(mainScreen.menu.menuButton);
        } catch (NoSuchElementException e) {
            if (((AndroidDriver) customDriver.getDriver()).currentActivity().equals("com.free.ads.mix.AdMobNativeFullScreenAdActivity")) {
                ((AndroidDriver) customDriver.getDriver()).navigate().back();
            } else {
                if (((AndroidDriver) customDriver.getDriver()).currentActivity().equals("com.google.android.gms.ads.AdActivity")) {
                    ((AndroidDriver) customDriver.getDriver()).navigate().back();
                    try {
                        customDriver.getAppiumDriver().findElement(mainScreen.menu.menuButton);
                    } catch (NoSuchElementException ex) {
                        ((AndroidDriver) customDriver.getDriver()).navigate().back();
                    }
                }
            }
            mainScreen.fluentVisibility(mainScreen.menu.menuButton, Duration.ofSeconds(20)).click();
        }


        new FullScreenADS(customDriver).closeADS();
        new GSMADS(customDriver).closeADS();
        WebElement e = customDriver.getAppiumDriver().findElement(mainScreen.menu.menuButton);
        mainScreen.fluentVisibility(mainScreen.menu.menuButton, Duration.ofSeconds(20)).click();
        customDriver.getAppiumDriver().findElement(mainScreen.menu.signIn).click();
        new LogIn(customDriver).logIn("nagaraju.batchu7+iOSYearlyExpired@gmail.com", "Test@123");
        Assert.fail();
    }

    public List<String> getAllServerNames() {
        List<String> serverNames = new ArrayList<>();
        Set<String> seenServers = new HashSet<>(); // Для предотвращения дублирования
        boolean isEndOfList = false;

        while (!isEndOfList) {
            // Получаем список элементов с экрана
            List<WebElement> serverElements = customDriver.getAppiumDriver().findElements(By.xpath("//androidx.recyclerview.widget.RecyclerView//android.widget.LinearLayout//android.widget.TextView[@resource-id='com.free.vpn.super.hotspot.open:id/item_country_name']"));

            // Сохраняем текст текущих элементов для дальнейшего сравнения
            List<String> currentScreenServers = new ArrayList<>();
            for (WebElement element : serverElements) {
                String serverName = element.getText();
                currentScreenServers.add(serverName);
                // Добавляем серверы в итоговый список, если они еще не были добавлены
                if (!seenServers.contains(serverName)) {
                    serverNames.add(serverName);
                    seenServers.add(serverName);
                }
            }

            // Выполняем свайп вверх для подгрузки новых элементов
            new FingerMove(customDriver.getAppiumDriver()).doSwipe(0.5, 0.8, 0.5, 0.2);

            // Даем время для подгрузки новых элементов
            try {
                Thread.sleep(250); // Необходимо подождать, пока новый контент загрузится
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // После свайпа снова получаем список серверов на экране
            List<WebElement> newServerElements = customDriver.getAppiumDriver().findElements(By.xpath("//androidx.recyclerview.widget.RecyclerView//android.widget.LinearLayout//android.widget.TextView[@resource-id='com.free.vpn.super.hotspot.open:id/item_country_name']"));

            // Сохраняем текст элементов после свайпа
            List<String> afterSwipeScreenServers = new ArrayList<>();
            for (WebElement element : newServerElements) {
                afterSwipeScreenServers.add(element.getText());
            }

            // Если список серверов до и после свайпа идентичен, значит мы достигли конца списка
            if (currentScreenServers.equals(afterSwipeScreenServers)) {
                isEndOfList = true;
            }
        }

        return serverNames;
    }*/
}

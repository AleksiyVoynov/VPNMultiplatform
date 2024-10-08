package apps.multiplatform.temp;

import apps.BaseTest;
import apps.common.AdsListener;
import apps.common.FingerMove;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Temp2Test extends BaseTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        AdsListener adListener = new AdsListener(customDriver);

        this.driver = new EventFiringDecorator<AppiumDriver>(adListener)
                .decorate(customDriver.getAppiumDriver());
    }

    @Test(priority = 2, description = "some")
    public void someTest() {
        driver.findElement(By.id("com.free.vpn.super.hotspot.open:id/btnMenu")).click();
        customDriver.getAppiumDriver().findElement(By.id("com.free.vpn.super.hotspot.open:id/btnMenu")).click();

        int a = 0;
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
    }
}

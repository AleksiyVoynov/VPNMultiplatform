package apps.multiplatform.temp;

import apps.BaseTest;
import apps.multiplatform.pages.mainPage.MainScreen;
import apps.multiplatform.pages.serverList.Server;
import apps.multiplatform.pages.serverList.ServerList;
import apps.multiplatform.utils.ServerUtils;
import com.google.common.collect.ImmutableMap;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.*;

public class TempTest extends BaseTest {
    private List<Server> servers = new ArrayList<>();
    private int clusters;

    @BeforeClass(description = "for range check")
    public void generateServers2() {
        servers.add(new Server(42, "Germany9 ( 3 )", "Germany35"));

        clusters = new MainScreen(customDriver)
                .tapIKEv2()
                .tapFastestLocation()
                .tapFree()
                .getAllClusters().size();

        Allure.addAttachment("number of servers", String.valueOf(servers.size()));
    }

    @DataProvider(name = "serverData")
    public Object[][] serverData() {
        Object[][] data = new Object[servers.size()][1];
        for (int i = 0; i < servers.size(); i++) {
            data[i][0] = servers.get(i);
        }
        return data;
    }

    @Test(priority = 1, description = "check connection IKEv2", dataProvider = "serverData")
    @Severity(SeverityLevel.CRITICAL)
    @Story("FREE IKEv2")
    @Description("""
            This test checks the availability of the server for the IKEv2 protocol.
            Connection to the server via UI
            Check whether there was a connection to the server""")
    public void Connectivity(Server server) {
        new MainScreen(customDriver)
                .tapIKEv2()
                .tapFastestLocation()
                .tapFree()
                .openCluster(server, clusters)
                .tapServer(server)
                .validateConnection(server)
                .tapBack();

    }

/*    @Test(priority = 3, description = "parsing server list FREE", dataProvider = "serverData", enabled = false)
    public void Temp3(Server server) {
        new MainScreen(customDriver)
                .tapIKEv2()
                .tapFastestLocation()
                .tapFree()
                .openCluster(server)
                .tapServer(server)
                .validateConnection(server)
                .tapBack();
    }*/

    @Test(priority = 2, description = "parsing server list FREE", enabled = false)
    public void Temp2() throws IOException {
        new MainScreen(customDriver)
                .tapIKEv2()
                .tapFastestLocation()
                .tapFree();

        String filePath = "src/main/java/apps/multiplatform/utils/servers.json";
        new ServerUtils().writeServersToJsonFile(servers, filePath);
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

    /*public static void main(String[] args) {
        // Заданные значения n и t
        double[] n_values = {1, 500, 1000};
        double[] t_values = {16, 45, 16};

        // Создаем объект для простой линейной регрессии
        SimpleRegression regression = new SimpleRegression();

        // Добавляем данные для регрессии
        for (int i = 0; i < n_values.length; i++) {
            regression.addData(n_values[i], t_values[i]);
        }

        // Получаем коэффициенты линейного уравнения
        double a = regression.getSlope();
        double b = regression.getIntercept();

        // Рассчитываем время для каждого сервера
        int n_servers = 1000;
        double total_time_seconds = 0;
        for (int i = 1; i <= n_servers; i++) {
            double time = a * i + b;
            total_time_seconds += time;
        }

        // Переводим в часы, минуты и секунды
        long hours = (long) (total_time_seconds / 3600);
        long minutes = (long) ((total_time_seconds % 3600) / 60);
        long seconds = (long) (total_time_seconds % 60);

        // Выводим результат
        System.out.println("Часы: " + hours + ", Минуты: " + minutes + ", Секунды: " + seconds);
    }*/
}

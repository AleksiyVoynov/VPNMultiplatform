package apps.multiplatform.temp;

import apps.BaseTest;
import apps.multiplatform.pages.login.LogIn;
import apps.multiplatform.pages.mainPage.MainScreen;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test()
    public void Connectivity() {
        new MainScreen(customDriver)
                .openMenu()
                .singIn("nagaraju.batchu7+AndroidMonthlyExpired@gmail.com", "Test@123");

        int a = 0;
    }
}

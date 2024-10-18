package apps.multiplatform.temp;

import apps.BaseTest;
import apps.multiplatform.pages.mainPage.MainScreen;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test()
    public void JustLogIn() {
        new MainScreen(customDriver)
                .openMenu()
                .singIn("nagaraju.batchu7+AndroidMonthlyExpired@gmail.com", "Test@123");

        int a = 0;
    }
}

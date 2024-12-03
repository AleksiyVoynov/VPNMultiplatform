package apps.multiplatform.login;

import apps.BaseTest;
import apps.pages.multiplatform.login.User;
import apps.pages.multiplatform.mainPage.MainScreen;
import org.testng.annotations.Test;

import static configs.ConfigLoader.getMyUser;

public class LoginTest extends BaseTest {

    @Test()
    public void JustLogIn() {
        new MainScreen(customDriver)
                .openMenu()
                .singIn(new User("nagaraju.batchu7+webExpiredMonthly@gmail.com", "Test@123"));
    }
}

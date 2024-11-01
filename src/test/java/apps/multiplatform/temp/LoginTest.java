package apps.multiplatform.temp;

import apps.BaseTest;
import apps.pages.multiplatform.mainPage.MainScreen;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test()
    public void JustLogIn() {
        new MainScreen(customDriver)
                .openMenu()
                .singIn("leshavoynov@gmail.com", "!testyO87");
    }
}

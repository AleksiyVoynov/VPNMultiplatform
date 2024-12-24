package apps.multiplatform;

import apps.BaseTest;
import apps.pages.multiplatform.login.User;
import apps.pages.multiplatform.mainPage.MainScreen;
import apps.pages.playStore.SubscriptionsPage;
import org.testng.annotations.Test;

public class HelpersIT extends BaseTest {

    @Test()
    public void logIn() {
        new MainScreen(customDriver)
                .openMenu()
                .singIn(new User("nagaraju+android@superunlimited.com", "Test@123"));
    }

    @Test()
    public void cancelSub() {
        SubscriptionsPage subscriptionsPage = new SubscriptionsPage(customDriver);
        subscriptionsPage.openGooglePlay();
        subscriptionsPage.cancelSubscription();
        subscriptionsPage.closeGooglePlay();
    }
}

package apps.multiplatform.helpers;

import apps.BaseTest;
import apps.multiplatform.pages.multiplatform.login.User;
import apps.multiplatform.pages.multiplatform.mainPage.MainScreen;
import apps.multiplatform.pages.playStore.SubscriptionsPage;
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

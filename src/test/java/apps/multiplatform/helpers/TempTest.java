package apps.multiplatform.helpers;

import apps.BaseTest;

import apps.multiplatform.pages.playStore.SubscriptionsPage;
import org.testng.annotations.Test;

public class TempTest extends BaseTest {


    @Test()
    public void Connectivity() {
        SubscriptionsPage subscriptionsPage = new SubscriptionsPage(customDriver);

        subscriptionsPage.openGooglePlay();
        subscriptionsPage.cancelSubscription();
        subscriptionsPage.closeGooglePlay();

        int a = 0;
    }
}

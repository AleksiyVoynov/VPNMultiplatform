package apps.multiplatform.ElementDisplayVerification.vip;

import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Regression tests")
public class MainPageTest {

    @Test(priority = 1, description = "Displayed elements")
    @Feature("VIP User")
    @Story("Main page objects")
    @Severity(SeverityLevel.NORMAL)
    @Description("""
            The test checks that the required elements are displayed on the page.""")
    public void DisplayedElements() {

    }

    @Test(priority = 2, description = "Not displayed elements")
    @Feature("VIP User")
    @Story("Main page objects")
    @Severity(SeverityLevel.NORMAL)
    @Description("""
            The test checks that the required elements are not displayed on the page.""")
    public void NotDisplayedElements() {

    }
}

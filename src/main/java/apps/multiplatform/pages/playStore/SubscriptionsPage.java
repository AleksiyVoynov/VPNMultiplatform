package apps.multiplatform.pages.playStore;

import driver.CustomDriver;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubscriptionsPage extends PlayStorePage {

    private final By userIcon = AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"Signed in as\")");
    private final By paymentsSubscription = AppiumBy.androidUIAutomator("new UiSelector().text(\"Payments & subscriptions\")");
    private final By subscriptionMenu = AppiumBy.androidUIAutomator("new UiSelector().text(\"Subscriptions\")");
    private final By activeSubscription = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Super Unlimited Proxy\")");
    private final By cancelButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"Cancel subscription\")");
    private final By noThanksButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"No thanks\")");
    private final By declineOption = AppiumBy.androidUIAutomator("new UiSelector().description(\"Decline to answer\")");
    private final By continueButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"Continue\")");
    private final By cancelSubscriptionButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"Cancel subscription\")");


    private final By expiredDate = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Your subscription will be canceled\")");

    public SubscriptionsPage(CustomDriver customDriver) {
        super(customDriver);
    }

    @Step("cancel the active subscription")
    public void cancelSubscription() {
        appiumDriver.findElement(userIcon).click();
        appiumDriver.findElement(paymentsSubscription).click();
        appiumDriver.findElement(subscriptionMenu).click();
        fluentVisibility(activeSubscription).click();
        fluentVisibility(cancelButton).click();
        appiumDriver.findElement(noThanksButton).click();
        appiumDriver.findElement(declineOption).click();
        appiumDriver.findElement(continueButton).click();
        parseDate(appiumDriver.findElement(expiredDate).getText());
        appiumDriver.findElement(cancelSubscriptionButton).click();
    }

    private void parseDate(String text) {
        text = text.replace("\u202F", " ").replace("\u00A0", " ");

        String regex = "\\b[A-Za-z]{3} \\d{1,2}, \\d{4}, \\d{1,2}:\\d{2} [APM]{2}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            String dateTime = matcher.group();
            System.out.println("Expired Date and Time: " + dateTime);
        } else {
            System.out.println("Date and Time not found.");
        }
    }
}

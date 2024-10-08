package apps.common;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;

public class FingerMove {
    private AppiumDriver appiumDriver;

    public FingerMove(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    @Step("finger tab by coordinate {x}, {y}")
    public void tapPosition(int x, int y) {
        PointerInput finger = new PointerInput(TOUCH, "finger");
        Sequence clickPosition = new Sequence(finger, 1);
        clickPosition
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        appiumDriver.perform(List.of(clickPosition));
    }

    @Step("swipe from {startX}, {startY} to {endX}, {endY}")
    public void doSwipe(int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(new Pause(finger, Duration.ofMillis(600)));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        appiumDriver.perform(List.of(swipe));
    }

    public void doSwipe(double startXPercent, double startYPercent, double endXPercent, double endYPercent) {

        Dimension size = appiumDriver.manage().window().getSize();
        int width = size.width;
        int height = size.height;

        int startX = (int) (width * startXPercent);
        int startY = (int) (height * startYPercent);
        int endX = (int) (width * endXPercent);
        int endY = (int) (height * endYPercent);

        doSwipe(startX, startY, endX, endY);
    }
}

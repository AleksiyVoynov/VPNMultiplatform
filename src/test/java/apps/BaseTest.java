package apps;

import configs.Config;
import configs.app.App;
import configs.app.MultiPlatformVPN;
import configs.devices.Android;
import configs.devices.Device;
import configs.devices.IOS;
import configs.platformConfig.android.AndroidConfig;
import configs.platformConfig.ios.IOSConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.SessionId;
import org.testng.Assert;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Base64;

public class BaseTest implements IHookable {
    private Device device = new Android(
            "SAMSUNG",
            "SM-T875",
            "13",
            "R52R301FPHA",
            new MultiPlatformVPN());
    protected Config config;
    public AppiumDriver appiumDriver;
    private final boolean recordingVideo = false;
    private boolean testIsThrowable = false;

    @BeforeClass
    @Step("setting up appium driver")
    @Parameters({"deviceName", "model", "version", "uDID", "app", "xcodeOrgId"})
    protected void setUp(@Optional("default") String deviceName,
                         @Optional("default") String model,
                         @Optional("default") String version,
                         @Optional("default") String uDID,
                         @Optional("default") String app,
                         @Optional("default") String xcodeOrgId) throws Exception {
        if (!deviceName.equals("default")) {
            if (!xcodeOrgId.equals("default")) {
                device = new IOS(deviceName, model, version, uDID, getApp(app), xcodeOrgId);
            } else {
                device = new Android(deviceName, model, version, uDID, getApp(app));
            }
        }

        if (device instanceof Android) {
            AndroidConfig androidConfig = new AndroidConfig();
            androidConfig.initDriver(device);
            config = new Config(androidConfig.android, device);
        } else if (device instanceof IOS) {
            IOSConfig iosConfig = new IOSConfig();
            iosConfig.initDriver(device);
            config = new Config(iosConfig.ios, device);
        } else {
            Assert.fail("unknown device platform");
        }
        config.appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        appiumDriver = config.appiumDriver;
    }

    @AfterClass
    @Step("tearing down appium driver")
    protected void tearDown() {
        config.appiumDriver.quit();
    }

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            testIsThrowable = true;
            try {
                takeScreenShot(testResult.getMethod().getMethodName());
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        } else {
            testIsThrowable = false;
        }
    }

    //if you need recording videos each of test you can use this
    @BeforeMethod
    public void beforeMethod() {
        if (recordingVideo) {
            if (device instanceof Android) {
                AndroidDriver androidDriver = (AndroidDriver) appiumDriver;
                String w = Integer.toString(androidDriver.manage().window().getSize().width / 2);
                String h = Integer.toString(androidDriver.manage().window().getSize().height / 2);
                androidDriver.startRecordingScreen(new IOSStartScreenRecordingOptions()
                        .withVideoFilters("scale=" + w + ":" + h));
                androidDriver.startRecordingScreen();

            } else if (device instanceof IOS) {
                IOSDriver iosDriver = (IOSDriver) appiumDriver;
                String w = Integer.toString(iosDriver.manage().window().getSize().width / 2);
                String h = Integer.toString(iosDriver.manage().window().getSize().height / 2);
                iosDriver.startRecordingScreen(new IOSStartScreenRecordingOptions()
                        .withVideoFilters("scale=" + w + ":" + h));
                iosDriver.startRecordingScreen();
            }
        }
    }

    @AfterMethod
    public void afterMethod() {
        if (appiumDriver != null) {
            SessionId sessionId = appiumDriver.getSessionId();
            String sessionIDString = sessionId != null ? sessionId.toString() : null;
            if (sessionIDString != null) {
                if (recordingVideo) {
                    String base64String = null;
                    if (device instanceof Android) {
                        AndroidDriver androidDriver = (AndroidDriver) appiumDriver;
                        base64String = androidDriver.stopRecordingScreen();

                    } else if (device instanceof IOS) {
                        IOSDriver iosDriver = (IOSDriver) appiumDriver;
                        base64String = iosDriver.stopRecordingScreen();
                    }
                    if (testIsThrowable) {
                        attachVideo(base64String);
                    }
                }
            } else {
                Allure.addAttachment("the result was: ", "session ID was null so video wasn't record");
                System.out.println("session ID was null so video wasn't record");
            }
        }
    }

    @Attachment(value = "video", type = "video/mp4")//+webm
    private byte[] attachVideo(String base64String) {
        //Allure.addAttachment("attachment name", "video/mp4", new ByteArrayInputStream(Base64.getDecoder().decode(base64String)), "mp4");
        return Base64.getDecoder().decode(base64String);
    }

    @Attachment(value = "Failure in method {0}", type = "image/png")
    private byte[] takeScreenShot(String ignoredMethodName) {
        return config.appiumDriver.getScreenshotAs(OutputType.BYTES);
    }

    private App getApp(String name) {
        return switch (name) {
            case "VPNMultiplatform" -> new MultiPlatformVPN();
            default -> throw new IllegalArgumentException("Invalid app: " + name);
        };
    }
}

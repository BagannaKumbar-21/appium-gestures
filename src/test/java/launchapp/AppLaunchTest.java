package launchapp;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class AppLaunchTest {

    @Test
    @Parameters({"apkFile"})
    public void shouldTestAndroidAppLaunch(String apkFile) throws MalformedURLException, InterruptedException {
        //Arrange
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");//optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);//optional
        options.setApp(System.getProperty("user.dir") + "/apps/android/"+apkFile);
        options.setDeviceName("Pixel 6");
        //Act
        WebDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
//        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@content-desc=\"open menu\"]")).click();
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]")).click();
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("bob@example.com");
        driver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys("10203040");
        driver.findElement(AppiumBy.accessibilityId("Login button")).click();

        //Assert
        driver.quit();
        Assert.assertTrue(true);
    }

    @Test
    public void shouldTestIOSAppLaunch() throws MalformedURLException {
        //Arrange
        XCUITestOptions options = new XCUITestOptions();
        options.setApp(System.getProperty("user.dir") + "/apps/ios/iOS-Simulator-MyRNDemoApp.1.3.0-162.zip");
        options.setDeviceName("iPhone 15");
        options.setPlatformVersion("17.0");
        AppiumDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
        //Act
        driver.findElement(AppiumBy.accessibilityId("tab bar option menu")).click();
        driver.findElement(AppiumBy.accessibilityId("menu item log in")).click();
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("bob@example.com");
        driver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys("10203040");
        driver.findElement(AppiumBy.accessibilityId("Login button")).click();
        driver.quit();
        //Assert
    }

    @Test
    @Parameters({"package", "activity"})
    public void shouldTestOpenCurrentActivity(String packageName, String activity) throws MalformedURLException {
        //Arrange
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");//optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);//optional
        options.setApp(System.getProperty("user.dir") + "/apps/android/Android-MyDemoAppRN.1.3.0.build-244.apk");
        options.setDeviceName("Pixel 6");
        options.setAppActivity(activity);
        options.setAppPackage(packageName);
        //Act
        WebDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
//        Activity activity1 = new Activity(packageName,activity);
//        ((JavascriptExecutor) driver)
//                .executeScript("mobile: startActivity",
//                        ImmutableMap.of("intent", packageName + "/" + activity));
        //Assert
        driver.quit();
        Assert.assertTrue(true);
    }
}

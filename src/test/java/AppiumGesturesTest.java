import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.example.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumGesturesTest {
    AndroidDriver driver;
    WebDriverWait wait;
    @BeforeClass(enabled = false)
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");//optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);//optional
        options.setApp(System.getProperty("user.dir") + "/apps/android/Android-MyDemoAppRN.1.3.0.build-244.apk");
        options.setDeviceName("Pixel 6");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }

    @BeforeClass
    public void setUpForAPIDemoApp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");//optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);//optional
        options.setApp(System.getProperty("user.dir") + "/apps/android/ApiDemos-debug.apk");
        options.setDeviceName("Pixel 6");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void shouldTestTap() {
        //Arrange
        WebElement openMenu = driver.findElement(By.xpath("//*[@content-desc=\"open menu\"]"));
        //Act
        Utils.tap(driver, openMenu);
        //Assert
    }

    @Test
    public void shouldTestDoubleTap() {
        //Arrange
        WebElement openMenu = driver.findElement(AppiumBy.accessibilityId("open menu"));
        //Act
        Utils.doubleTap(driver, openMenu);
        //Assert
    }

    @Test
    public void shouldTestLongPress() throws MalformedURLException {
        //use apiDemo app
        driver.findElement(AppiumBy.xpath(".//*[@text='Views']")).click();
        driver.findElement(AppiumBy.xpath(".//*[@text='Expandable Lists']")).click();
        driver.findElement(AppiumBy.xpath(".//*[@text='1. Custom Adapter']")).click();
        WebElement element = driver.findElement(AppiumBy.xpath("//*[@text='People Names']"));


        new Actions(driver).clickAndHold(element).perform();
        Utils.longPress(driver, element);
    }

    @Test
    public void shouldTestScroll() {
        //Arrange

        //Act
        Utils.swipeOrScroll(driver);
        //Assert
    }

    @Test
    public void shouldTestZoom() {
        //Arrange
        driver.findElement(AppiumBy.accessibilityId("open menu")).click();
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item drawing\"]"))
                .click();

        WebElement element = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"drawing screen\"]"));
        wait.until(ExpectedConditions.visibilityOf(element));
        //Act
        Utils.zoom(driver, element);
        //Assert
    }

    @Test
    public void shouldTestDragAndDrop(){
        //Arrange
        driver.findElement(AppiumBy.xpath(".//*[@text='Views']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(".//*[@text='Drag and Drop']"))).click();
        WebElement sourceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("io.appium.android.apis:id/drag_dot_1")));
        WebElement destinationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("io.appium.android.apis:id/drag_dot_2")));

        //Act

        Utils.dragAndDrop(driver,sourceElement,destinationElement);

        //Assert
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class utilityMethod {
    private static AndroidDriver driver;
    private DesiredCapabilities capabilities = new DesiredCapabilities();
    private static ExtentTest test;
    private static ExtentReports report;

    @BeforeTest
    public void setCapabilities() throws InterruptedException, MalformedURLException {

        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
        capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(10000);



    }

    @Test
    public void skipSignIn()
    {
            try {
                WebElement logo = driver.findElementById("in.amazon.mShop.android.shopping:id/sso_splash_logo");
                Assert.assertNotNull(logo);
                WebElement skipButton = driver.findElementById("in.amazon.mShop.android.shopping:id/skip_sign_in_button");
                if(skipButton.isDisplayed())
                {
                    skipButton.click();
                }

            }catch(Exception e)
            {
                System.out.print(e);
            }
    }
    @Test(priority = 2)
    public void homePageValidation()
    {
            WebElement buggerButton= driver.findElementById("in.amazon.mShop.android.shopping:id/action_bar_burger_icon");
            if(buggerButton.isDisplayed())
            {
                System.out.print("Amazon Home Page landed");
                buggerButton.click();
                WebElement signInBanner= driver.findElementById("in.amazon.mShop.android.shopping:id/gno_greeting_text_view");
                System.out.println(signInBanner.getText());
                Assert.assertEquals(signInBanner.getText(),"Hello. Sign In");
            }


    }

@AfterTest
    public void quit()
    {
        driver.quit();
    }
}

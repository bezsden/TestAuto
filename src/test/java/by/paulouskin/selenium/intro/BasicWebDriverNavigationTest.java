package by.paulouskin.selenium.intro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BasicWebDriverNavigationTest {

    WebDriver wd;
    String targetAddress;

    @BeforeMethod
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        //options.setHeadless(true);
        options.setAcceptInsecureCerts(false);
        wd = new ChromeDriver(options);
        targetAddress = "http://www.etsy.com";
        wd.get("http://www.google.com");
        wd.navigate().refresh();
        wd.navigate().to(targetAddress);
        wd.navigate().back();
        wd.navigate().forward();
    }

    @Test
    public void FindElementsTest() {
        assertEquals(true,true);
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }

}

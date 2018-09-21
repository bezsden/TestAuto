package by.paulouskin.selenium.selenium.etsy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;

public class EtsyBaseTest {
    protected static WebDriver webDriver;

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","D:\\java_practice\\chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        webDriver = new ChromeDriver(opt);
        webDriver.manage().deleteAllCookies();
    }

    @AfterAll
    public static void tearDown() {
        webDriver.quit();
    }


}

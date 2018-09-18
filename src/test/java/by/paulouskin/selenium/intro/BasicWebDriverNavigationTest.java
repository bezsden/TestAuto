package by.paulouskin.selenium.intro;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
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
    public void CookieManipulationTest() {
       wd.manage().window().maximize();
        Cookie cookie = new Cookie("SeleniumIntroductionCookie","SICookieValue");
        wd.manage().addCookie(cookie);
        System.out.println(wd.manage().getCookieNamed("SeleniumIntroductionCookie"));
        wd.manage().deleteCookie(cookie);
        MatcherAssert.assertThat(wd.manage().getCookieNamed("SeleniumIntroductionCookie"), is(nullValue()));
    }

    @Test
    public void JavascriptExecutionTest() {
        JavascriptExecutor jse = (JavascriptExecutor) wd;
        String title = (String)jse.executeScript("return document.title");
        boolean jquery_loaded = (boolean) jse.executeScript("return jQuery.active == 0");
        //MatcherAssert.assertThat(title, containsString("Etsy.com"));
        MatcherAssert.assertThat(jquery_loaded, is(equalTo(true)));
    }


    @AfterMethod
    public void tearDown() {
        wd.quit();
    }

}

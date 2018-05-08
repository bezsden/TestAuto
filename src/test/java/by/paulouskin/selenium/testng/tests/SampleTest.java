package by.paulouskin.selenium.testng.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SampleTest {
    static WebDriver wd;

    @BeforeMethod
    public static void setUp() {
        wd = new ChromeDriver();
        wd.get("https://www.google.com");
    }
    @Test
    public void RunGoogleChrome() {
        Assert.assertEquals("Google",wd.getTitle());
    }

    @AfterMethod
    public static void tearDown() {
        wd.close();
    }
}

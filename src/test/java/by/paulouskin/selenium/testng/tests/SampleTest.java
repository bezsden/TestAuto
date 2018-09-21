package by.paulouskin.selenium.testng.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.WatchEvent;

public class SampleTest {
    static WebDriver wd;

    @BeforeMethod
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver","D:\\java_practice\\chromedriver.exe");

        wd = new ChromeDriver();
        wd.get("https://www.google.com");
    }
    @Test
    public void RunGoogleChrome() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","D:\\java_practice\\chromedriver.exe");

        String aderss = "http:/www.google.com";
        String query ="selenium";
        wd.get(aderss);
        WebElement searchField=wd.findElement(By.xpath("//input[@name='q']"));
        searchField.clear();
        searchField.sendKeys(query);
        searchField.submit();
        Thread.sleep(3000);
        Assertions.assertTrue(wd.getTitle().contains(query));

       // Assert.assertEquals("Google",wd.getTitle());
    }

    @AfterMethod
    public static void tearDown() {
        wd.close();
    }
}

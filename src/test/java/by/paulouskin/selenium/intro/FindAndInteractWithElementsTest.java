package by.paulouskin.selenium.intro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FindAndInteractWithElementsTest {

    private WebDriver wd;

    @BeforeMethod
    public void setUp() {
        wd = new ChromeDriver();
        wd.get("http://www.phptravels.net");
    }

    @Test
    public void FindElementByTagName() {

    }

    @Test
    public void FindAndClickEnterTextCheckElementsTest() {

    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }

}

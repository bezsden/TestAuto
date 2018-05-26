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

import static by.paulouskin.selenium.intro.PhpTravelsSelectors.*;

public class FindAndInteractWithElementsTest {

    private WebDriver wd;

    @BeforeMethod
    public void setUp() {
        wd = new ChromeDriver();
        wd.get("http://www.phptravels.net");
    }

    @Test
    public void FindElementByTagName() {
        WebElement navigation_bar = wd.findElement(NAVIGATION_PANEL);
        System.out.println(navigation_bar.getText());
        WebElement page_header = wd.findElement(PAGE_HEADER);
        System.out.println(page_header.getCssValue("box-sizing"));
    }

    @Test
    public void FindAndClickEnterTextCheckElementsTest() {
        /*WebElement flight_tab = wd.findElement(NAVIGATION_PANEL)
                .findElements(By.tagName("li"))
                .stream()
                .filter(we -> we.getText().equalsIgnoreCase("flights"))
                .findFirst()
                .get();
        flight_tab.click();*/
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }

}

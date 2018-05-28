package by.paulouskin.selenium.intro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindElementsHelpers {

    public static WebElement findElementWithWait(WebDriver wd, By locator) {
        return new WebDriverWait(wd,30)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

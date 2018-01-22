package page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class GMailAbstractPage {
    protected WebDriver driver;

    public GMailAbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void waitForElement(List<WebElement> element) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(element));
    }
}

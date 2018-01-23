package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class HihglightElement {
    public void highlither(WebDriver driver, WebElement element) {
        String bgColor = element.getCssValue("backgroundColor");
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor)driver);
        javascriptExecutor.executeScript("arguments[0].style.backgroundColor = '" + "yellow" +
                "'", element);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        javascriptExecutor.executeScript("arguments[0].style.backgroundColor = '"
                + bgColor + "'", element);
    }
}

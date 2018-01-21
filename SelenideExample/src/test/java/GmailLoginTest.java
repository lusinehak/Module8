
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class GmailLoginTest {

    @BeforeClass
    public void openMail() {
        open("https://accounts.google.com");
    }

    @Test(priority = 1)
    public void login() throws InterruptedException {
        $(By.id("identifierId")).setValue("testcaseforselenium").pressEnter();
        $(By.name("password")).setValue("9386206687").pressEnter();
        $(By.className("paz5i")).shouldHave(text("My Account"));
    }

    @Test(priority = 2)
    public void composeMail() {
        $(By.cssSelector(".WaidBe")).click();
        $(By.xpath("//*[contains(@class, 'T-I-KE')]")).click();
        $(By.name("to")).setValue("lusine-hakobian@mail.ru");
        $(By.name("subjectbox")).setValue("my mail");
        $(By.xpath("//div[@aria-label='Message Body']")).setValue("This is a test mail");
        $(By.xpath("//div[text()='Send']")).click();
        $(By.xpath("//a[contains(@title, 'Sent Mail')]")).click();
        $(By.xpath("//span[contains(text(), 'my mail')]")).shouldHave(text("my mail"));
    }

    @Test(priority = 3)
    public void logOff() {
        $(By.xpath("//span[@class='gb_ab gbii']")).click();
        $(By.xpath(("//a[text()='Sign out']"))).click();
        $(By.id("headingText")).shouldHave(text("Hi Test"));
    }

    @AfterClass
    public void quit() {
        close();
    }

}

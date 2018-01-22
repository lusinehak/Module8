import business.objects.Email;
import business.objects.User;
import page.objects.LoginPage;
import page.objects.EmailActionsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.util.concurrent.TimeUnit.SECONDS;

public class LoginAndSendEmailTest {
    private WebDriver driver;
    private User user;
    private Email email;
    private String URL = "https://accounts.google.com";

    @BeforeClass()
    public void init() {
        user = new User();
        email = new Email();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
    }

    @Test
    public void login() {
        boolean isLoggedIn = new LoginPage(driver).logInAccount(user.getUserName(), user.getPassword()).isLoggedIn();
        Assert.assertTrue(isLoggedIn, "Login issue");
    }

    @Test(dependsOnMethods = "login")
    public void composeAndSave() {
        boolean isSavedInDrafts = new EmailActionsPage(driver).composeAndSaveAsDraft(email.getReceiver(), email.getSubject(),
                email.getContent()).goToFolder("Drafts").isItemExists(email.getSubject());
        Assert.assertTrue(isSavedInDrafts, "Item is not saved in drafts");
    }

    @Test(dependsOnMethods = "composeAndSave")
    public void checkFields() {
        new EmailActionsPage(driver).selectDraftItem(email.getSubject());
        String receiver = new EmailActionsPage(driver).getReceiver(email.getReceiver());
        String subject = new EmailActionsPage(driver).getSubject();
        String content = new EmailActionsPage(driver).getContent();
        Assert.assertEquals(receiver, email.getReceiver(), "Invalid receiver");
        Assert.assertEquals(subject, email.getSubject(), "Invalid subject");
        Assert.assertEquals(content, email.getContent(), "Invalid content");
    }

    @Test(dependsOnMethods = "checkFields")
    public void send() {
        boolean isSent = new EmailActionsPage(driver).sendMail().goToFolder("Sent Mail").isItemExists(email.getSubject());
        Assert.assertTrue(isSent, "Item is not sent");
    }

    @Test(dependsOnMethods = "send")
    public void checkDraft() {
        boolean isAbsent = new EmailActionsPage(driver).goToFolder("Drafts").isDraftEmpty();
        Assert.assertTrue(isAbsent, "Item exists in draft folder.");
    }

    @AfterClass
    public void quit() {
        new EmailActionsPage(driver).logOut();
        driver.quit();
    }
}

package tests.mail;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import patterns.singleton.WebDriverSingleton;
import tests.mail.page_object.DraftsPage;
import tests.mail.page_object.InboxPage;
import tests.mail.page_object.MainPage;
import tests.mail.page_object.SentMessagesPage;

import java.util.concurrent.TimeUnit;

public class MailTest {
    WebDriver driver;
    @BeforeClass(description = "Start browser")
    private void initBrowser() {
        driver= WebDriverSingleton.getWebDriverInstance();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @AfterClass(description = "close browser" )
    public void kill(){
        driver.close();
    }

    @Test(description = "Login To the System ", priority = 0)
    public void loginToSystem() {
        MainPage mainPage = new MainPage(driver).open();
        mainPage.enterLogin().moveToPasswordEnter().enterPassword().clickLoginButton();
    }
    @Test(description = "Login To the System ", priority = 1)
    public void saveLetterToDraft() {
        DraftsPage draftsPage = new InboxPage(driver).writeMailClick().fillLetterInputs().pressSaveLetterOnDraft().closeAndMoveToDraftsPage();
    }
    @Test(description = "check whether letter was placed to the Drafts folder", priority = 2)
    public void openMessageThatWasPlacedToDrafts() throws InterruptedException {
        Thread.sleep(4000);
        new DraftsPage(driver).openAndCheckMessagePlacedToDrafts().clickOnSendButton();
    }
    @Test(description = "check whether message was placed to sent messages tap" , priority = 3)
    public void checkIsMessageSent() throws InterruptedException {
        SentMessagesPage sentMessagesPage = new DraftsPage(driver).moveSentMessagesPage().checkWhetherMessageIsAtSentMessagesTap();
    }

}

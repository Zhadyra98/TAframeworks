package tests.mail.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class DraftsPage extends AbstractPage{

    private static final By DRAFT_MESSAGES_LIST_LOCATOR =By.xpath("//span[@class='ll-sj__normal']");
    private static final By SEND_BUTTON_LOCATOR = By.xpath("//span[@tabindex='570']/span[@class='button2__txt']");
    private static final By SENT_MESSAGE_LOCATOR =By.xpath("//div[@class='layer-sent-page']");
    private static final By SENT_MESSAGES_TAP_LOCATOR=By.xpath("//div[@class='nav__folder-name__txt'][contains(text(),'Отправленные')]");
    public DraftsPage (WebDriver driver){
        super(driver);
    }
    public DraftsPage openAndCheckMessagePlacedToDrafts(){
        WebElement neededDraftMessage=null;
        boolean isPresentMessage = false;
        List<WebElement> draftMessages = driver.findElements(DRAFT_MESSAGES_LIST_LOCATOR);
        for(WebElement it : draftMessages){
            if(it.getText().contains(new InboxPage(driver).firstLetter.getTitle())){
                isPresentMessage = true;
                neededDraftMessage = it;
                break;
            }
        }
        if (isPresentMessage) {
            neededDraftMessage.click();
        }
        else {
            //TODO: add logger
            System.out.println("Message is not placed to the Draft Folder");
        }
        return this;
    }
    public DraftsPage clickOnSendButton(){
        driver.findElement(SEND_BUTTON_LOCATOR).click();
        WebDriverWait wait =  new WebDriverWait(driver,20);;
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(SENT_MESSAGE_LOCATOR)));
        return this;
    }
    public SentMessagesPage moveSentMessagesPage(){
        driver.findElement(SENT_MESSAGES_TAP_LOCATOR).click();
        return new SentMessagesPage(driver);
    }
}

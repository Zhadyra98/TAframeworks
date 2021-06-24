package tests.mail.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import tests.mail.business_object.Letter;

import java.util.List;

public class SentMessagesPage extends AbstractPage{
    public SentMessagesPage(WebDriver driver){
        super(driver);
    }
    public SentMessagesPage checkWhetherMessageIsAtSentMessagesTap() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> sentMessages = driver.findElements(By.xpath("//span[@class='ll-sj__normal']"));
        Assert.assertEquals(sentMessages.get(0).getText(),(new InboxPage(driver).firstLetter.getTitle()));
        return this;
    }
}

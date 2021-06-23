package tests.mail.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tests.mail.business_object.Letter;

public class InboxPage extends AbstractPage{
    private static final By WRITE_MAIL_TAP_LOCATOR=By.xpath("//span[contains(text(),'Написать письмо')]");
    public Letter firstLetter = new Letter("oralkhanova.zhadyra@gmail.com","Email from Selenium WebDriver","Hello, this email was send by the work of Selenium Webdriver");
    private static final By EMAIL_INPUT_LOCATOR=By.xpath("/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div/div/div[1]/div/div[2]/div/div/label/div/div/input");
    private static final By SUBJECT_INPUT_LOCATOR=By.xpath("/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[3]/div[1]/div[2]/div/input");
    private static final By MAIN_TEXT_INPUT_LOCATOR=By.xpath("/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[5]/div/div/div[2]/div[1]/div[2]");
    private static final By SAVE_LETTER_BUTTON_LOCATOR =By.xpath("/html/body/div[15]/div[2]/div/div[2]/div[1]/span[2]/span/span");
    private static final By CLOSE_BUTTON_LOCATOR =By.xpath("//button[@tabindex='700'][@class='container--2lPGK type_base--rkphf color_base--hO-yz']");
    private static final By DRAFTS_PAGE_TAP_LOCATOR =By.xpath("//div[@class='nav__folder-name__txt'][contains(text(),'Черновики')]");
    public InboxPage(WebDriver driver){
        super(driver);
    }
    public InboxPage writeMailClick(){
        driver.findElement(WRITE_MAIL_TAP_LOCATOR).click();
        return this;
    }
    public InboxPage fillLetterInputs(){
        driver.findElement(EMAIL_INPUT_LOCATOR).sendKeys(firstLetter.getToWhom());
        driver.findElement(SUBJECT_INPUT_LOCATOR).sendKeys(firstLetter.getTitle());
        driver.findElement(MAIN_TEXT_INPUT_LOCATOR).sendKeys(firstLetter.getToWhom());
        return this;
    }
    public InboxPage pressSaveLetterOnDraft(){
        driver.findElement(SAVE_LETTER_BUTTON_LOCATOR).click();
        return this;
    }
    public DraftsPage closeAndMoveToDraftsPage(){
        driver.findElement(CLOSE_BUTTON_LOCATOR).click();
        driver.findElement(DRAFTS_PAGE_TAP_LOCATOR).click();
        return new DraftsPage(driver);
    }
}

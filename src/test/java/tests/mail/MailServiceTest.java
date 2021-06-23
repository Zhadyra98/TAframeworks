package tests.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


import java.util.List;


public class MailServiceTest {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeTest
    public void beforeTest(){
        driver = new ChromeDriver();
        driver.get("https://mail.ru/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().setScriptTimeout(5000, TimeUnit.MILLISECONDS);
        wait = new WebDriverWait(driver,20);
    }
    @AfterTest
    public void afterTest(){
        driver.quit();
    }
    @Test
    public void mailServiceTest() throws InterruptedException {
        String email = "jaka9898@mail.ru";
        String password = "mydreamis98";
        String emailToSend = "oralkhanova.zhadyra@gmail.com";
        String subjectOfEmail = "Email from Selenium WebDriver";
        String bodyOfEmail="Hello, this email was send by the work of Selenium Webdriver";
        WebElement inputEmailField = driver.findElement(By.xpath("//input[@placeholder='Имя ящика']"));
        inputEmailField.sendKeys(email);
        WebElement enterPasswordButton = driver.findElement(By.xpath("//button[@data-testid='enter-password']"));
        enterPasswordButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"mailbox\"]/form[1]/div[2]/input"))));
        WebElement inputPasswordField = driver.findElement(By.xpath("//*[@id=\"mailbox\"]/form[1]/div[2]/input"));
        inputPasswordField.sendKeys(password);
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"mailbox\"]/form[1]/button[2]"));
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"ph-whiteline\"]/div/div[2]/div[2]/span[2]"))));
        WebElement logoWithEmail = driver.findElement(By.xpath("//*[@id=\"ph-whiteline\"]/div/div[2]/div[2]/span[2]"));
        Assert.assertTrue(logoWithEmail.getText().contains(email));
        WebElement ret=driver.findElement(By.xpath("//span[contains(text(),'Написать письмо')]"));
        ret.click();
        WebElement emailInput = driver.findElement(By.xpath("/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div/div/div[1]/div/div[2]/div/div/label/div/div/input"));
        WebElement subjectInput = driver.findElement(By.xpath("/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[3]/div[1]/div[2]/div/input"));
        WebElement mainBodyInput = driver.findElement(By.xpath("/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[5]/div/div/div[2]/div[1]/div[2]"));
        emailInput.sendKeys(emailToSend);
        subjectInput.sendKeys(subjectOfEmail);
        mainBodyInput.sendKeys(bodyOfEmail);
        WebElement saveEmail = driver.findElement(By.xpath("/html/body/div[15]/div[2]/div/div[2]/div[1]/span[2]/span/span"));
        saveEmail.click();
        WebElement closeButton = driver.findElement(By.xpath("//button[@tabindex='700'][@class='container--2lPGK type_base--rkphf color_base--hO-yz']"));
        closeButton.click();
        WebElement draftsTab = driver.findElement(By.xpath("//div[@class='nav__folder-name__txt'][contains(text(),'Черновики')]"));
        draftsTab.click();
        Thread.sleep(4000);
        List<WebElement> draftMessages = driver.findElements(By.xpath("//span[@class='ll-sj__normal']"));
        WebElement neededDraftMessage=null;
        boolean isPresentMessage = false;
        for(WebElement it : draftMessages){
            if(it.getText().contains(subjectOfEmail)){
                isPresentMessage = true;
                neededDraftMessage = it;
                break;
            }
        }
        Assert.assertTrue(isPresentMessage);
        if (isPresentMessage) {
            neededDraftMessage.click();
        }
        else {
            System.out.println("Message is not placed to the Draft Folder");
        }
        WebElement sendButton = driver.findElement(By.xpath("//span[@tabindex='570']/span[@class='button2__txt']"));
        sendButton.click();
        WebElement sentMessageWindow = driver.findElement(By.xpath("//div[@class='layer-sent-page']"));
        wait.until(ExpectedConditions.invisibilityOf(sentMessageWindow));
        isPresentMessage=false;
        draftMessages = driver.findElements(By.xpath("//span[@class='ll-sj__normal']"));
        for(WebElement it : draftMessages){
            if(it.getText().contains(subjectOfEmail)){
                isPresentMessage = true;
                break;
            }
        }
        Assert.assertFalse(isPresentMessage);
        WebElement sentMessagesTab = driver.findElement(By.xpath("//div[@class='nav__folder-name__txt'][contains(text(),'Отправленные')]"));
        sentMessagesTab.click();
        Thread.sleep(5000);
        //wait.until(ExpectedConditions.elementToBeSelected(sentMessagesTab));
        List<WebElement> sentMessages = driver.findElements(By.xpath("//span[@class='ll-sj__normal']"));
        Assert.assertEquals(sentMessages.get(0).getText(),subjectOfEmail);
        WebElement iconOfAccount = driver.findElement(By.xpath("//div[@class='ph-project ph-project__account svelte-a0l97g'][@data-testid='whiteline-account']"));
        iconOfAccount.click();
        WebElement logOffButton = driver.findElement(By.xpath("//a[@class='ph-item ph-item__hover-active svelte-ylcw5t']/div[contains(text(),'Выйти')]"));
        logOffButton.click();
    }
}

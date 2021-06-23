package tests.mail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tests.mail.page_object.MainPage;

import java.util.concurrent.TimeUnit;

public class MailTest {
    private WebDriver driver;
    @BeforeTest(description = "Start browser")
    private void initBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @AfterTest(description = "close browser")
    public void kill(){
        //driver.close();
    }

    @Test(description = "Change the city at Maps tab")
    public void changeCityOfSystemTest() {
        MainPage mainPage = new MainPage(driver).open();
        /*MapsPage mapsPage = new HomePage(driver).open().startMapsPage().selectSemeyAsCity();
        Assert.assertEquals(mapsPage.getCity(),"Семей");*/
    }
}
